import React from "react";

const TextInput = ({ label, value, onChange, error }) => (
  <div className="input-container">
    <label>
      {label}:
      <input
        type="text"
        value={value}
        onChange={(e) => onChange(e.target.value)}
      />
      {error && <div className="error">{error}</div>}
    </label>
  </div>
);

export default TextInput;
