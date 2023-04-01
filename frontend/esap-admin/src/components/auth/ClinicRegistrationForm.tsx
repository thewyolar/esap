import React, { useState } from "react";
import "./auth.scss";
import { ClinicDTO } from "../../model/dto/ClinicDTO";

interface ClinicRegistrationFormProps {
  onSubmit: (clinicData: ClinicDTO) => void;
}

const ClinicRegistrationForm: React.FC<ClinicRegistrationFormProps> = ({ onSubmit }) => {
  const [clinicName, setClinicName] = useState("");
  const [clinicAddress, setClinicAddress] = useState("");

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const clinicData: ClinicDTO = {
      name: clinicName,
      address: clinicAddress
    };

    onSubmit(clinicData);
  };

    return (
      <div className="Auth-form-container">
        <form className="Auth-form" onSubmit={handleSubmit}>
          <div className="Auth-form-content">
            <h3 className="Auth-form-title">Регистрация поликлиники</h3>
            <div className="form-group mt-3">
              <label htmlFor="clinicName">Название</label>
              <input id="clinicName" type="text" className="form-control mt-1" placeholder="Название" value={clinicName}
                     onChange={(event) => setClinicName(event.target.value)} required />
            </div>
            <div className="form-group mt-3">
              <label htmlFor="clinicAddress">Адрес</label>
              <input id="clinicAddress" type="text" className="form-control mt-1" placeholder="Адрес" value={clinicAddress}
                     onChange={(event) => setClinicAddress(event.target.value)} required />
            </div>
            <div className="d-grid gap-2 my-3">
              <button type="submit" className="btn btn-primary">
                Зарегистрировать поликлинику
              </button>
            </div>
          </div>
        </form>
      </div>
    );
};

export default ClinicRegistrationForm;
