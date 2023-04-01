import React, { useState } from 'react';
import RegistrationForm from '../../components/auth/RegistrationForm';
import ClinicRegistrationForm from '../../components/auth/ClinicRegistrationForm';
import { DoctorDTO } from "../../model/dto/DoctorDTO";
import { ClinicDTO } from "../../model/dto/ClinicDTO";
import {ClinicRegistrationDTO} from "../../model/dto/ClinicRegistrationDTO";
import HttpService from "../../service/HttpService";
import axios from "axios";

const RegistrationPage: React.FC = () => {
  const [doctorData, setDoctorData] = useState<DoctorDTO>();
  const [showClinicForm, setShowClinicForm] = useState<boolean>(false);

  const handleDoctorSubmit = (data: DoctorDTO) => {
    setDoctorData(data);
    setShowClinicForm(true);
  };

  const handleClinicSubmit = (clinicData: ClinicDTO) => {
    if (doctorData && clinicData) {
      const requestData: ClinicRegistrationDTO = {
        clinic: clinicData,
        doctor: doctorData,
      };
      HttpService.registrationClinics(requestData)
        .then((response) => console.log(response))
        .catch((error) => console.error(error))
    }
  };

  return (
    <div>
      {!showClinicForm && <RegistrationForm onSubmit={handleDoctorSubmit} />}
      {showClinicForm && <ClinicRegistrationForm onSubmit={handleClinicSubmit} />}
    </div>
  );
};

export default RegistrationPage;
