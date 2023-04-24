import React, {useEffect, useState} from "react";
import HttpService from "../../service/HttpService";
import {DataGrid, GridColDef, ruRU} from "@mui/x-data-grid";
import {Link} from "react-router-dom";
import {BorderAll, DeleteOutline} from "@mui/icons-material";
import {DoctorDTO} from "../../model/dto/DoctorDTO";
import ScheduleModal from "../../components/scheduleModal/ScheduleModal";
import './doctorList.scss';

const DoctorList: React.FC = () => {
  const [data, setData] = useState<DoctorDTO[]>([]);
  const [open, setOpen] = useState(false);
  const [selectedDoctor, setSelectedDoctor] = useState<DoctorDTO>();

  useEffect(() => {
    HttpService.getDoctorList()
      .then(response => setData(response))
      .catch(error => console.error(error));
  }, []);

  const handleOpen = (doctor: DoctorDTO) => {
    setSelectedDoctor(doctor);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleDelete = (id: number) => {
    setData(data.filter(item => item.id !== id));
  };

  const columns: GridColDef[] = [
    {
      field: 'id',
      headerName: 'ID',
      width: 50,
    },
    {
      field: 'firstName',
      headerName: 'Имя',
      width: 100,
      renderCell: (params) => {
        return (
          <div className='doctorListField'>{params.row.firstName}</div>
        );
      },
    },
    {
      field: 'patronymic',
      headerName: 'Отчество',
      width: 140,
    },
    {
      field: 'lastName',
      headerName: 'Фамилия',
      width: 120,
    },
    {
      field: 'gender',
      headerName: 'Пол',
      width: 80,
      renderCell: (params) => {
        return (
          <div className='doctorListField'>
            {params.row.gender === 1 ? 'мужской' : 'женский'}
          </div>
        );
      },
    },
    {
      field: 'specialization',
      headerName: 'Специализация',
      width: 150,
    },
    {
      field: 'action',
      headerName: 'Действие',
      width: 250,
      renderCell: (params) => {
        return (
          <>
            <Link to={`/doctor/${params.row.id}`}>
              <button className='editButton'>Изменить</button>
            </Link>
            <button className='editButton' onClick={() => handleOpen(params.row)}>Расписание</button>
            <DeleteOutline
              className='deleteButton'
              onClick={() => handleDelete(params.row.id)}
            />
          </>
        );
      },
    },
  ];

  return (
    <div className='doctorListPage'>
      <DataGrid
        localeText={ruRU.components.MuiDataGrid.defaultProps.localeText}
        rows={data}
        disableSelectionOnClick
        columns={columns}
        pageSize={13}
        rowsPerPageOptions={[5]}
        checkboxSelection
        sx={{border: "none"}}
      />
      {selectedDoctor && (
        <ScheduleModal
          schedules={selectedDoctor.schedules}
          open={open}
          onClose={handleClose}
        />
      )}
    </div>
  );
};

export default DoctorList;