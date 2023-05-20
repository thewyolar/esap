import "./patientList.scss";
import { DataGrid, GridColDef, ruRU } from "@mui/x-data-grid";
import React, { useEffect, useState } from "react";
import { Clear, DeleteOutline, Search } from "@mui/icons-material";
import { Link } from "react-router-dom";
import { Patient } from "../../model/Patient";
import HttpService from "../../service/HttpService";
import {
  Box,
  Button,
  IconButton,
  InputAdornment,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  TextField,
} from "@mui/material";
import MedicalInformationIcon from "@mui/icons-material/MedicalInformation";
import EditIcon from "@mui/icons-material/Edit";
import AddIcon from "@mui/icons-material/Add";

const PatientList: React.FC = () => {
  const [data, setData] = useState<Patient[]>([]);
  const [searchTerm, setSearchTerm] = useState<string>("");
  const [maxPage, setMaxPage] = useState<number>(0);
  const [page, setPage] = useState<number>(0);
  const [pageSize, setPageSize] = useState<number>(10);

  useEffect(() => {
    HttpService.getPatientList(page)
      .then((response) => setData(response))
      .catch((error) => console.error(error));
    if (maxPage == 0) {
      HttpService.getPatientCount()
        .then((response) => setMaxPage(response))
        .catch((error) => console.error(error));
    }
  }, [page]);

  const handlePageChange = (newPage: number) => {
    if (newPage < 0) {
      return;
    }
    setData([]);
    setPage(newPage);
  };

  const handlePageSizeChange = (newPageSize: number) => {
    setPageSize(newPageSize);
  };

  const handleSearch = () => {
    const [name, lastName, patronymic] = searchTerm.trim().split(/\s+/);
    HttpService.searchPatientList(name, lastName, patronymic, page)
      .then((response) => setData(response))
      .catch((error) => console.error(error));
  };

  const handleDelete = (id: number) => {
    setData(data.filter((item) => item.id !== id));
  };

  const handleAddPatient = () => {
    // TODO: Логика добавления пациента
  };

  const columns: GridColDef[] = [
    {
      field: "id",
      headerName: "ID",
      width: 50,
    },
    {
      field: "firstName",
      headerName: "Имя",
      width: 100,
      renderCell: (params) => {
        return <div className="patientListField">{params.row.firstName}</div>;
      },
    },
    {
      field: "patronymic",
      headerName: "Отчество",
      width: 140,
    },
    {
      field: "lastName",
      headerName: "Фамилия",
      width: 120,
    },
    {
      field: "birthDate",
      headerName: "Дата рождения",
      width: 120,
    },
    {
      field: "gender",
      headerName: "Пол",
      width: 80,
      renderCell: (params) => {
        return (
          <div className="patientListField">
            {params.row.gender === 1 ? "мужской" : "женский"}
          </div>
        );
      },
    },
    {
      field: "phoneNumber",
      headerName: "Телефон",
      width: 130,
    },
    {
      field: "action",
      headerName: "Действие",
      width: 250,
      renderCell: (params) => {
        return (
          <>
            <Link to={`/patient/${params.row.id}`}>
              <IconButton
                color="primary"
                aria-label="edit patient"
                component="label"
              >
                <EditIcon />
              </IconButton>
            </Link>
            <Link to={`/medicalCard/${params.row.id}`}>
              <IconButton
                color="primary"
                aria-label="patient card"
                component="label"
              >
                <MedicalInformationIcon />
              </IconButton>
            </Link>
            <IconButton
              color="primary"
              aria-label="delete patient"
              component="label"
              onClick={() => handleDelete(params.row.id)}
            >
              <DeleteOutline className="deleteButton" />
            </IconButton>
          </>
        );
      },
    },
  ];

  return (
    <div className="patientListPage">
      <TableContainer sx={{ minHeight: "100%" }}>
      <TextField
          value={searchTerm}
          onChange={e => setSearchTerm(e.target.value)}
          variant='outlined'
          fullWidth
          size='small'
          placeholder='Поиск пациента'
          InputProps={{
            startAdornment: (
              <InputAdornment position='start'>
                <Search onClick={handleSearch} />
              </InputAdornment>
            ),
            endAdornment: (
              <IconButton onClick={() => setSearchTerm('')}>
                <Clear />
              </IconButton>
            ),
          }}
          InputLabelProps={{ shrink: true }}
          sx={{ width: '800px' }}
      />
        <Table sx={{ minWidth: 650, minHeight: 600 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>id</TableCell>
              <TableCell>Имя</TableCell>
              <TableCell align="center">Отчество</TableCell>
              <TableCell align="center">Фамилия</TableCell>
              <TableCell align="center">Пол</TableCell>
              <TableCell align="center">Специализация</TableCell>
              <TableCell align="center">Действие</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {data.map((row) => (
              <TableRow key={row.id}>
                <TableCell sx={{ padding: "5px" }} align="center">
                  {row.id}
                </TableCell>
                <TableCell sx={{ padding: "5px" }} align="center">
                  {row.firstName}
                </TableCell>
                <TableCell sx={{ padding: "5px" }} align="center">
                  {row.patronymic}
                </TableCell>
                <TableCell sx={{ padding: "5px" }} align="center">
                  {row.lastName}
                </TableCell>
                <TableCell sx={{ padding: "5px" }} align="center">
                  {row.gender === 1 ? "мужской" : "женский"}
                </TableCell>
                <TableCell sx={{ padding: "5px" }} align="center">
                  <>
                    <Link to={`/patient/${row.id}`}>
                      <IconButton
                        color="primary"
                        aria-label="edit patient"
                        component="label"
                      >
                        <EditIcon />
                      </IconButton>
                    </Link>
                    <Link to={`/medicalCard/${row.id}`}>
                      <IconButton
                        color="primary"
                        aria-label="patient card"
                        component="label"
                      >
                        <MedicalInformationIcon />
                      </IconButton>
                    </Link>
                    <IconButton
                      color="primary"
                      aria-label="delete patient"
                      component="label"
                      onClick={() => handleDelete(row.id)}
                    >
                      <DeleteOutline className="deleteButton" />
                    </IconButton>
                  </>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        <div className="navigation">
          <IconButton
            color="primary"
            onClick={() => handlePageChange(page - 1)}
            size="small"
          >
            <svg>
              <path d="M15.41 16.09l-4.58-4.59 4.58-4.59L14 5.5l-6 6 6 6z"></path>
            </svg>
          </IconButton>
          {page + 1}
          <IconButton
            color="primary"
            onClick={() => handlePageChange(page + 1)}
          >
            <svg>
              <path d="M8.59 16.34l4.58-4.59-4.58-4.59L10 5.75l6 6-6 6z"></path>
            </svg>
          </IconButton>
        </div>
      </TableContainer>
    </div>
  );
};

export default PatientList;
