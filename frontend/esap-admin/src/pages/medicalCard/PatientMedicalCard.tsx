import { Box, Typography } from "@mui/material";
import Modal from "@mui/material/Modal";
import { DataGrid, GridColDef, ruRU } from "@mui/x-data-grid";
import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { MedicalCard } from "../../model/MedicalCard";
import { MedicalRecord } from "../../model/MedicalRecord";
import HttpService from "../../service/HttpService";
import "./patientMedicalCard.scss";

const PatientMedicalCard: React.FC = (onClose) => {

  let { patientId } = useParams();
  let patientIdInt = parseInt(patientId!);
  const [data, setData] = useState<MedicalCard>();
  useEffect(() => {
    HttpService.getMedicalCard(patientIdInt)
      .then((response) => setData(response))
      .catch((error) => console.error(error));
  }, []);

  // const [selectedMedicalRecord, setSelectedSchedule] = useState<MedicalRecord>();
  const [open, setOpen] = useState(false);
  const handleOpen = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const columns: GridColDef[] = [
    {
      field: 'id',
      headerName: 'ID',
      width: 50,
    },
    {
      field: 'fioandSpecializationDoctor',
      headerName: 'Врач',
      width: 400,
    },
    {
      field: 'date',
      headerName: 'Дата',
      width: 140,
    },
    {
      field: 'action',
      headerName: 'Действие',
      width: 170,
      renderCell: () => {
        return (
          <>
            <button className='editButton' onClick={handleOpen}>Изменить</button>
          </>
        );
      },
    },
  ];

  return (
    <div className="patienPage">
      <div className="titleContainer">
        <h1>Карта пациента</h1>
      </div>
      <div className="patientContainer">
        <div className="show">
          <div className="top">
            <div className="title">
              <span className="username">{data?.patient.firstName}</span>
              <span className="username">{data?.patient.lastName}</span>
              <span className="username">{data?.patient.patronymic}</span>
            </div>
          </div>
          <div className="bottom">
            <div className="info">
              <span className="infoTitle">{data?.patient.gender === 1 ? 'мужской' : 'женский'}</span>
            </div>
            <div className="info">
              <span className="infoTitle">{data?.patient.birthDate}</span>
            </div>
            <span className="title">{data?.patient.address}</span>
            <div className="info">
              <span className="infoTitle">{data?.patient.phoneNumber}</span>
            </div>
            <div className="info">
              <span className="infoTitle">{data?.patient.email}</span>
            </div>
          </div>
        </div>
        <div className="medicalCard">
        <DataGrid
        localeText={ruRU.components.MuiDataGrid.defaultProps.localeText}
        rows={data?.medicalRecord || []}
        disableSelectionOnClick
        columns={columns}
        pageSize={13}
        rowsPerPageOptions={[5]}
        checkboxSelection
        />
        
        </div>
      </div>
      {(
      <Modal
      open={open}
      onClose={handleClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
      >
        <Box>
          <Typography id="modal-modal-title" variant="h6" component="h2">
            Text in a modal
          </Typography>
          <Typography id="modal-modal-description" sx={{ mt: 2 }}>
            Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
          </Typography>
        </Box>
      </Modal>
      )}
    </div>
  )
}

export default PatientMedicalCard;
