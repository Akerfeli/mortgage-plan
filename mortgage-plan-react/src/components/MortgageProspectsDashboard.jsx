import React from "react";
import ProspectsTable from "./ProspectsTable";

const MortgageProspectsDashboard = () => {
  return (
    <div className="main">
      <div id="dashboard-header">
        <h2>Mortgage Prospects</h2>
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
