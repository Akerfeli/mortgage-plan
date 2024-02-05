import React, { useEffect } from "react";
import ProspectsTable from "./ProspectsTable";
import Modal from "./Modal";
import NewProspectForm from "./NewProspectForm";
import useModal from "../hooks/useModal";

const MortgageProspectsDashboard = () => {
  const { isShowing, toggleModal } = useModal();

  const onAddProspect = () => {
    console.log("prospect added");
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
        <ProspectsTable />
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
