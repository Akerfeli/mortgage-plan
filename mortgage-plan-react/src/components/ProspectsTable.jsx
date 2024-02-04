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
        setError(error);
      });
  }, []);

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
            <td>{prospect.totalLoan}</td>
            <td>{prospect.interest}</td>
            <td>{prospect.years}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default ProspectsTable;
