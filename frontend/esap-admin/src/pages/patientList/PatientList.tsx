import './patientList.scss';
import {DataGrid, GridColDef, ruRU} from '@mui/x-data-grid';
import React, { useEffect, useState } from 'react';
import {Clear, DeleteOutline, Search} from '@mui/icons-material';
import {Link} from 'react-router-dom';
import {Patient} from "../../model/Patient";
import HttpService from "../../service/HttpService";
import {Box, Button, IconButton, InputAdornment, TextField} from "@mui/material";

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
              <Button variant='contained' color='primary' style={{ marginRight: '10px' }} size="small">
                Изменить
              </Button>
            </Link>
            <Link to={`/medicalCard/${params.row.id}`}>
              <Button variant='contained' color='primary' style={{ marginRight: '10px' }} size="small">
                Карта
              </Button>
            </Link>
            <DeleteOutline
              className='deleteButton'
              onClick={() => handleDelete(params.row.id)}
            />
          </>
        );
      }
    }
  ];

  const filteredData = data.filter(
    item =>
      item.firstName.toLowerCase().includes(searchTerm.toLowerCase()) ||
      item.lastName.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className='patientListPage'>
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
        <Button
          variant='contained'
          color='primary'
          onClick={handleAddPatient}
          sx={{ marginLeft: '10px' }}
        >
          Добавить пациента
        </Button>
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
