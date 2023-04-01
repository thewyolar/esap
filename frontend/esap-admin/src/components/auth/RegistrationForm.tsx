import React, {useState} from 'react';
import "./auth.scss";
import {DoctorDTO} from "../../model/dto/DoctorDTO";

interface RegistrationFormProps {
  onSubmit: (doctorData: DoctorDTO) => void;
}

const RegistrationForm: React.FC<RegistrationFormProps> = ({ onSubmit }) => {
  const [doctorFirstName, setDoctorFirstName] = useState('');
  const [doctorPatronymic, setDoctorPatronymic] = useState('');
  const [doctorLastName, setDoctorLastName] = useState('');
  const [doctorSpecialization, setDoctorSpecialization] = useState('');

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    const doctorData: DoctorDTO = {
      firstName: doctorFirstName,
      patronymic: doctorPatronymic,
      lastName: doctorLastName,
      specialization: doctorSpecialization
    };

    onSubmit(doctorData);
  };

  return (
    <div className="Auth-form-container">
      <form className="Auth-form" onSubmit={handleSubmit}>
        <div className="Auth-form-content">
          <h3 className="Auth-form-title">Регистрация</h3>
          <div className="form-group mt-3">
            <label htmlFor="doctorName">Имя</label>
            <input id="doctorName" type="text" className="form-control mt-1" placeholder="Имя" value={doctorFirstName}
                   onChange={(e) => setDoctorFirstName(e.target.value)} required />
          </div>
          <div className="form-group mt-3">
            <label htmlFor="doctorLastName">Фамилия</label>
            <input id="doctorLastName" type="text" className="form-control mt-1" placeholder="Фамилия" value={doctorLastName}
                   onChange={(e) => setDoctorLastName(e.target.value)} required />
          </div>
          <div className="form-group mt-3">
            <label htmlFor="doctorPatronymic">Отчество</label>
            <input id="doctorPatronymic" type="text" className="form-control mt-1" placeholder="Отчество" value={doctorPatronymic}
                   onChange={(e) => setDoctorPatronymic(e.target.value)} />
          </div>
          <div className="form-group mt-3">
            <label htmlFor="doctorSpecialization">Специализация</label>
            <input id="doctorSpecialization" type="text" className="form-control mt-1" placeholder="Специализация" value={doctorSpecialization}
                   onChange={(e) => setDoctorSpecialization(e.target.value)} required />
          </div>
          <div className="d-grid gap-2 my-3">
            <button type="submit" className="btn btn-primary">
              Зарегистрироваться
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default RegistrationForm;