import React, { useState } from "react";
import ProspectsTable from "./ProspectsTable";
import Modal from "./Modal";
import NewProspectForm from "./NewProspectForm";
import useModal from "../hooks/useModal";

const MortgageProspectsDashboard = () => {
  const { isShowing, toggleModal } = useModal();
  const [refreshTable, setRefreshTable] = useState(false);

  const onAddProspect = () => {
    // Trigger a refetch of data for ProspectsTable
    toggleModal();
    setRefreshTable((prevRefreshTable) => !prevRefreshTable);
  };

  return (
    <>
      <div className="main">
        <div id="dashboard-header">
          <h1>Mortgage Prospects</h1>
          <button className="new-prospect-button" onClick={toggleModal}>
            + New Prospect
          </button>
        </div>
        <ProspectsTable key={refreshTable} />
      </div>

      {/* Render the modal only if isShowing is true */}
      {isShowing && (
        <Modal onCloseModal={toggleModal}>
          <NewProspectForm onFormSubmitted={onAddProspect} />
        </Modal>
      )}
    </>
  );
};

export default MortgageProspectsDashboard;
