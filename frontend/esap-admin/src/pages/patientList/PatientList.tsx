import './patientList.scss';
import { DataGrid, GridColDef } from '@mui/x-data-grid';
import React, { useState } from 'react';
import { DeleteOutline } from '@mui/icons-material';
import { patientsData } from '../../dummyData';
import { Link } from 'react-router-dom';

interface Patient {
  id: number;
  firstName: string;
  patronymic: string;
  lastName: string;
  birthDate: string;
  gender: string;
  address: string;
  phoneNumber: string;
  email: string;
}

const PatientList: React.FC = () => {
  const [data, setData] = useState<Patient[]>(patientsData);

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
    },
    {
      field: 'address',
      headerName: 'Адрес',
      width: 200,
    },
    {
      field: 'phoneNumber',
      headerName: 'Телефон',
      width: 130,
    },
    {
      field: 'email',
      headerName: 'Почта',
      width: 180,
    },
    {
      field: 'action',
      headerName: 'Действие',
      width: 150,
      renderCell: (params) => {
        return (
          <>
            <Link to={`/user/${params.row.id}`}>
              <button className='editButton'>Изменить</button>
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
        rows={data}
        disableSelectionOnClick
        columns={columns}
        pageSize={13}
        rowsPerPageOptions={[5]}
        checkboxSelection
      />
    </div>
  );
};

export default PatientList;
