import React, { useEffect, useState } from "react";
import axios from "axios";

const ProspectsTable = () => {
  const [prospects, setProspects] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    axios
      .get("http://localhost:8080/prospects")
      .then((response) => {
        setProspects(response.data);
        setError(null);
      })
      .catch((error) => {
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          setError(`Request failed with status ${error.response.status}`);
        } else if (error.request) {
          // The request was made but no response was received
          setError("No response received");
        } else {
          // Something happened in setting up the request that triggered an Error
          setError(error.message);
        }
      });
  }, []);

  // Moves decimal point two steps to the left
  const moveDecimalPoint = (number) => {
    // Convert the number to a string
    let numberString = number.toString();

    // If the number is less than 10, add a leading zero
    if (number < 10) {
      numberString = "0" + numberString;
    }

    // If the number is less than 100, add a leading zero
    if (number < 100) {
      numberString = "0" + numberString;
    }

    // Insert a dot before the second last digit
    numberString = numberString.slice(0, -2) + "." + numberString.slice(-2);

    // Remove trailing zeros
    numberString = numberString.replace(/\.?0+$/, "");

    // Remove trailing dot
    numberString = numberString.replace(/\.$/, "");

    return numberString;
  };

  if (error) {
    return <p>Error when fetching prospects: {error}</p>;
  }

  if (!prospects && !error) {
    return <p>Loading...</p>;
  }

  return (
    <table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Total Loan</th>
          <th>Interest</th>
          <th>Years</th>
          <th>Monthly Payment</th>
        </tr>
      </thead>
      <tbody>
        {prospects.map((prospect, index) => (
          <tr key={index}>
            <td>{prospect.name}</td>
            <td>{moveDecimalPoint(prospect.totalLoanCents)}</td>
            <td>{moveDecimalPoint(prospect.interestRateBsp)}%</td>
            <td>{prospect.years}</td>
            <td>{moveDecimalPoint(prospect.monthlyPayment)}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default ProspectsTable;
