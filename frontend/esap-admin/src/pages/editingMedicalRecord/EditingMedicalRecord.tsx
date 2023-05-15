import { IconButton } from "@mui/material";
import { useEffect, useState } from "react";
import { Button } from "react-bootstrap";
import { useParams } from "react-router-dom";
import { Analysis } from "../../model/Analysis";
import { MedicalCard } from "../../model/MedicalCard";
import { MedicalRecord } from "../../model/MedicalRecord";
import { Patient } from "../../model/Patient";
import { TokenStorageService } from "../../service/auth/TokenStorageService";
import HttpService from "../../service/HttpService";
import "./editingMedicalRecord.scss";

const EditingMedicalRecord: React.FC = (onClose) => {
  const [tokenStorageService] = useState<TokenStorageService>(
    new TokenStorageService()
  );
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
      .then((response) => {})
      .catch((error) => {});
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
            <Button>
              Добавить поле
            </Button>
          </div>
          <button type="submit" onClick={handleSubmit}>
            Сохранить
          </button>
        </form>
        <div className="show">
          <div className="top">
            <div className="title">
              <span className="username">{data?.firstName}</span>
              <span className="username">{data?.lastName}</span>
              <span className="username">{data?.patronymic}</span>
              <span className="infoTitle">
                {data?.gender === 1 ? "мужской" : "женский"}
              </span>
              <span className="infoTitle">{data?.birthDate}</span>
              <span className="infoTitle">{data?.address}</span>
              <span className="infoTitle">{data?.phoneNumber}</span>
              <span className="infoTitle">{data?.email}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EditingMedicalRecord;
