import './patientList.scss';
import {DataGrid, GridColDef, ruRU} from '@mui/x-data-grid';
import React, { useEffect, useState } from 'react';
import {Clear, DeleteOutline, Search} from '@mui/icons-material';
import {Link} from 'react-router-dom';
import {Patient} from "../../model/Patient";
import HttpService from "../../service/HttpService";
import {Box, IconButton, InputAdornment, TextField, Typography} from "@mui/material";
import MedicalInformationIcon from '@mui/icons-material/MedicalInformation';
import EditIcon from "@mui/icons-material/Edit";
import AddIcon from '@mui/icons-material/Add';

const PatientList: React.FC = () => {
  const [data, setData] = useState<Patient[]>([]);
  const [searchTerm, setSearchTerm] = useState<string>('');

  useEffect(() => {
    HttpService.getPatientList()
      .then(response => setData(response))
      .catch(error => console.error(error));
  }, []);

  const handleDelete = (id: number) => {
    setData(data.filter(item => item.id !== id));
  };

  const handleAddPatient = () => {
    // TODO: Логика добавления пациента
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
              <IconButton color="primary" aria-label="edit patient" component="label">
                <EditIcon />
              </IconButton>
            </Link>
            <Link to={`/medicalCard/${params.row.id}`}>
              <IconButton color="primary" aria-label="patient card" component="label">
                <MedicalInformationIcon />
              </IconButton>
            </Link>
            <IconButton color="primary" aria-label="delete patient" component="label">
              <DeleteOutline
                className='deleteButton'
                onClick={() => handleDelete(params.row.id)}
              />
            </IconButton>
          </>
        );
      }
    }
  ];

  return (
    <div className='patientListPage' style={{ backgroundColor: '#f2f2f2' }}>
      <Box sx={{display: 'flex', alignItems: 'center', marginBottom: '10px'}}>
        <TextField
          value={searchTerm}
          onChange={e => setSearchTerm(e.target.value)}
          variant='outlined'
          fullWidth
          size='small'
          InputProps={{
            startAdornment: (
              <InputAdornment position='start'>
                <Search />
              </InputAdornment>
            ),
            endAdornment: (
              <IconButton onClick={() => setSearchTerm('')}>
                <Clear />
              </IconButton>
            ),
          }}
          sx={{ marginRight: '10px' }}
        />
        <IconButton color="primary" aria-label="add patient" component="label" onClick={handleAddPatient}>
          <AddIcon />
        </IconButton>
      </Box>
      <DataGrid
        localeText={ruRU.components.MuiDataGrid.defaultProps.localeText}
        rows={data}
        disableSelectionOnClick
        columns={columns}
        pageSize={13}
        rowsPerPageOptions={[13]}
        checkboxSelection
        sx={{border: "none"}}
      />
    </div>
  );
};

export default PatientList;
