import React, {useEffect, useState} from "react";
import HttpService from "../../service/HttpService";
import {Patient} from "../../model/Patient";
import Details from "../../components/details/Details";
import EditPatientForm from "../../components/editPatient/EditPatientForm";
import {useParams} from "react-router-dom";

const PatientPage: React.FC = () => {
  let { patientId } = useParams();
  let patientIdInt = parseInt(patientId!);
  const [patient, setPatient] = useState<Patient>();

  useEffect(() => {
    HttpService.getPatient(patientIdInt)
      .then(response => setPatient(response))
      .catch(error => console.error(error));
  }, []);

  return (
    <div className='userPage'>
      <div className="titleContainer">
        <h3>Пациент</h3>
      </div>
      <div className="userContainer">
        {patient && <Details patient={patient} />}
        {patient && <EditPatientForm patient={patient} />}
      </div>
    </div>
  );
};

export default PatientPage;