import './patientList.scss';
import {DataGrid, GridColDef, ruRU} from '@mui/x-data-grid';
import React, { useEffect, useState } from 'react';
import { DeleteOutline } from '@mui/icons-material';
import { Link } from 'react-router-dom';
import { Patient } from "../../model/Patient";
import HttpService from "../../service/HttpService";

const PatientList: React.FC = () => {
  const [data, setData] = useState<Patient[]>([]);

  useEffect(() => {
    HttpService.getPatientList()
      .then(response => setData(response))
      .catch(error => console.error(error));
  }, []);

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
          <div className='patientListField'>{params.row.firstName}</div>
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
      field: 'birthDate',
      headerName: 'Дата рождения',
      width: 120,
    },
    {
      field: 'gender',
      headerName: 'Пол',
      width: 80,
      renderCell: (params) => {
        return (
          <div className='patientListField'>
            {params.row.gender === 1 ? 'мужской' : 'женский'}
          </div>
        );
      },
    },
    {
      field: 'phoneNumber',
      headerName: 'Телефон',
      width: 130,
    },
    {
      field: 'action',
      headerName: 'Действие',
      width: 250,
      renderCell: (params) => {
        return (
          <>
            <Link to={`/patient/${params.row.id}`}>
              <button className='editButton'>Изменить</button>
            </Link>
            <Link to={`/medicalCard/${params.row.id}`}>
              <button className='editButton'>Карта</button>
            </Link>
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
    <div className='patientListPage'>
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
    </div>
  );
};

export default PatientList;
