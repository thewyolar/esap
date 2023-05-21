import { Alert, Button, IconButton } from "@mui/material";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { Analysis } from "../../model/Analysis";
import { MedicalCard } from "../../model/MedicalCard";
import { MedicalRecord } from "../../model/MedicalRecord";
import { Patient } from "../../model/Patient";
import { TokenStorageService } from "../../service/auth/TokenStorageService";
import HttpService from "../../service/HttpService";
import PatientMedicalCard from "../medicalCard/PatientMedicalCard";
import "./editingMedicalRecord.scss";

const EditingMedicalRecord: React.FC = (onClose) => {
  const [tokenStorageService] = useState<TokenStorageService>(
    new TokenStorageService()
  );
  const [success, setSuccess] = useState<boolean>(false);
  let { patientId } = useParams();
  let patientIdInt = parseInt(patientId!);
  const [data, setData] = useState<Patient>();
  useEffect(() => {
    HttpService.getPatient(patientIdInt)
      .then((response) => setData(response))
      .catch((error) => console.error(error));
  }, []);

  const [medicalRecord, setMedicalRecord] = useState<string>();
  const [analysisFields, setAnalysisFields] = useState<string[]>([""]);

  const handleSubmit = (event: React.FormEvent) => {
    event.preventDefault();
    if (!medicalRecord && !analysisFields[0]) {
      return;
    }
    let analysis: Analysis[] = [];
    analysisFields.map((nameAnalysis) => {
      if (nameAnalysis != "" && nameAnalysis != " ") {
        analysis.push({ name: nameAnalysis } as Analysis);
      }
    });
    const record: MedicalRecord = {
      record: medicalRecord || "",
      fioAndSpecializationDoctor: tokenStorageService.getLogin(),
      date: new Date().toISOString(),
      analyzes: analysis,
    } as MedicalRecord;
    console.log(record);
    HttpService.saveMedicalRecord(patientIdInt, record)
      .then((response) => {
        setSuccess(true);
        setTimeout(() => {
          window.location.reload();
        }, 500);
      })
      .catch((error) => {});
  };


  const completeReception = (event: React.FormEvent) => {
    
  };

  const handleAddAnalysisField = () => {
    setAnalysisFields([...analysisFields, ""]);
  };

  const handleAnalysisFieldChange = (index: number, value: string) => {
    const newAnalysisFields = [...analysisFields];
    newAnalysisFields[index] = value;
    setAnalysisFields(newAnalysisFields);
  };

  const renderAnalysisFields = () => {
    return analysisFields.map((field, index) => (
      <div key={index}>
        <input
          type="text"
          value={field}
          onChange={(e) => handleAnalysisFieldChange(index, e.target.value)}
        />
      </div>
    ));
  };

  return (
    <div className="patienPage">
      <div className="titleContainer">
        <h1>Добавление записи в карту пациента</h1>
      </div>
      {success && <Alert severity="success">Данные сохранены</Alert>}
      <div className="patientContainer">
        <form>
          <div className="form-group">
            <label htmlFor="complaints">Запись:</label>
            <textarea
              onChange={(e) => setMedicalRecord(e.target.value)}
              id="complaints"
              name="complaints"
            ></textarea>
          </div>
          <div>
            <h2>Анализы</h2>
            <div className="analysis">{renderAnalysisFields()}</div>
            <Button variant="contained" type="button" onClick={handleAddAnalysisField}>
              Добавить поле
            </Button>
          </div>
          <Button color="success" variant="contained" type="submit" onClick={handleSubmit}>
            Сохранить
          </Button>
          <Button color="error" variant="outlined" type="button" onClick={completeReception}>
            Завершить прием
          </Button>
        </form>
        <PatientMedicalCard></PatientMedicalCard>
      </div>
    </div>
  );
};

export default EditingMedicalRecord;
