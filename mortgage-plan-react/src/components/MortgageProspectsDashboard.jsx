import React from "react";
import ProspectsTable from "./ProspectsTable";

const MortgageProspectsDashboard = () => {
  return (
    <div className="main">
      <div id="dashboard-header">
        <h1>Mortgage Prospects</h1>
        <button
          className="new-prospect-button"
          onClick={() => console.log("Add new prospect clicked")}
        >
          + New Prospect
        </button>
      </div>
      <ProspectsTable />
    </div>
  );
};

export default MortgageProspectsDashboard;
