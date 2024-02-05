import React, { useState, useEffect } from "react";
import TextInput from "./TextInput";
import PropTypes from "prop-types";
import axios from "axios";
import { moveDecimalLeft, moveDecimalRight } from "../utils/FinanicalUtils";

const AddProspectForm = ({ onFormSubmitted }) => {
  const [name, setName] = useState("");
  const [totalLoan, setTotalLoan] = useState("");
  const [interest, setInterest] = useState("");
  const [years, setYears] = useState("");
  const [monthlyPayment, setMonthlyPayment] = useState(null);
  const [formErrors, setFormErrors] = useState({});

  useEffect(() => {
    // Reset monthlyPayment whenever any of the input fields change
    setMonthlyPayment(null);
  }, [name, totalLoan, interest, years]);

  const validateForm = () => {
    const errors = {};

    if (!name.trim()) {
      errors.name = "Name is required";
    }

    if (name.includes(",")) {
      errors.name = "Name cannot contain a comma";
    }

    if (name.length > 50) {
      errors.name = "Name cannot exceed 50 characters";
    }

    if (
      !totalLoan.trim() ||
      isNaN(parseFloat(totalLoan)) ||
      totalLoan.includes(",")
    ) {
      errors.totalLoan = "Total Loan must be a valid number";
    }

    if (
      !interest.trim() ||
      isNaN(parseFloat(interest)) ||
      interest > 100 ||
      interest.includes(",")
    ) {
      errors.interest =
        "Interest Rate must be a valid number an cannot be larger than 100";
    }

    if (!years.trim() || isNaN(parseInt(years))) {
      errors.years = "Years must be a valid number";
    }

    setFormErrors(errors);

    return Object.keys(errors).length === 0; // Return true if there are no errors
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (!validateForm()) {
      return;
    }

    try {
      await axios.post("http://localhost:8080/prospects", {
        name: name,
        totalLoanCents: moveDecimalRight(totalLoan),
        interestRateBps: moveDecimalRight(interest),
        years: years,
      });
      onFormSubmitted();
    } catch (error) {
      console.error("Error creating prospect:", error);
    }
  };

  const handleCalculateClick = async () => {
    try {
      const response = await axios.post(
        "http://localhost:8080/prospects/calculateMonthlyPayment",
        {
          name: "",
          totalLoanCents: moveDecimalRight(totalLoan),
          interestRateBps: moveDecimalRight(interest),
          years: years,
        }
      );

      // Assuming your API returns the calculated monthly payment in the response
      setMonthlyPayment(response.data);
    } catch (error) {
      console.error("Error calculating monthly payment:", error);
    }
  };

  return (
    <form className="new-prospect-form" onSubmit={handleSubmit}>
      <h1>Add New Prospect</h1>
      <TextInput
        label="Customer Name"
        value={name}
        onChange={setName}
        error={formErrors.name}
      />
      <br />
      <TextInput
        label="Total Loan"
        value={totalLoan}
        onChange={setTotalLoan}
        error={formErrors.totalLoan}
      />
      <br />
      <TextInput
        label="Interest Rate %"
        value={interest}
        onChange={setInterest}
        error={formErrors.interest}
      />
      <br />
      <TextInput
        label="Years"
        value={years}
        onChange={setYears}
        error={formErrors.years}
      />
      <br />
      {monthlyPayment ? (
        <>
          <p>Monthly Payment: {moveDecimalLeft(monthlyPayment)}</p>
          <div style={{ textAlign: "center" }}>
            <button type="button" onClick={handleSubmit}>
              Add Prospect
            </button>
          </div>
        </>
      ) : (
        <div style={{ textAlign: "center", color: "#888" }}>
          Calculate monthly payment
          <button type="button" onClick={handleCalculateClick}>
            Calculate
          </button>
        </div>
      )}
    </form>
  );
};

AddProspectForm.propTypes = {
  onFormSubmitted: PropTypes.func.isRequired,
};

export default AddProspectForm;
