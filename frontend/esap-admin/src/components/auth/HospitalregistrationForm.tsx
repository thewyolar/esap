import React, { useState } from "react";
import "./auth.scss";

const HospitalRegistrationForm: React.FC = () => {
    const [hospitalName, setHospitalName] = useState("");
    const [hospitalAddress, setHospitalAddress] = useState("");

    return (
      <div className="Auth-form-container">
        <form className="Auth-form">
          <div className="Auth-form-content">
            <h3 className="Auth-form-title">Регистрация поликлиники</h3>
            <div className="form-group mt-3">
              <label htmlFor="hospitalName">Название:</label>
              <input
                  id="hospitalName"
                  type="text"
                  className="form-control mt-1"
                  value={hospitalName}
                  onChange={(event) => setHospitalName(event.target.value)}
                  required
              />
            </div>
            <div className="form-group mt-3">
              <label htmlFor="hospitalAddress">Адрес:</label>
              <input
                  id="hospitalAddress"
                  type="text"
                  className="form-control mt-1"
                  value={hospitalAddress}
                  onChange={(event) => setHospitalAddress(event.target.value)}
                  required
              />
            </div>
            <div className="d-grid gap-2 my-3">
              <button type="submit" className="btn btn-primary">
                Зарегистрировать
              </button>
            </div>
          </div>
        </form>
      </div>
    );
};

export default HospitalRegistrationForm;
