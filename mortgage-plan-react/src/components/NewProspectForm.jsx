import React, { useState, useEffect } from "react";
import PropTypes from "prop-types";

const AddProspectForm = ({ onFormSumbitted }) => {
  const [name, setName] = useState("");
  const [totalLoan, setTotalLoan] = useState("");
  const [interest, setInterest] = useState("");
  const [years, setYears] = useState("");
  const [monthlyPayment, setMonthlyPayment] = useState(null);

  useEffect(() => {
    // Reset monthlyPayment whenever any of the input fields change
    setMonthlyPayment(null);
  }, [name, totalLoan, interest, years]);

  const handleSubmit = (e) => {
    e.preventDefault();

    // ToDo: Validate the form data

    const newProspect = {
      name,
      totalLoan: parseFloat(totalLoan),
      interest: parseFloat(interest),
      years: parseInt(years),
    };

    onFormSumbitted();
  };

  const handleCalculateClick = () => {
    setMonthlyPayment("test");
  };

  return (
    <form className="new-prospect-form" onSubmit={handleSubmit}>
      <h1>Add New Prospect</h1>
      <label>
        Name:
        <input
          type="text"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
      </label>
      <br />
      <label>
        Total Loan:
        <input
          type="text"
          value={totalLoan}
          onChange={(e) => setTotalLoan(e.target.value)}
        />
      </label>
      <br />
      <label>
        Interest Rate:
        <input
          type="text"
          value={interest}
          onChange={(e) => setInterest(e.target.value)}
        />
      </label>
      <br />
      <label>
        Years:
        <input
          type="text"
          value={years}
          onChange={(e) => setYears(e.target.value)}
        />
      </label>
      <br />
      {monthlyPayment ? (
        <>
          <p>Monthly Payment: {monthlyPayment}</p>
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
  onAddProspect: PropTypes.func.isRequired,
};

export default AddProspectForm;
