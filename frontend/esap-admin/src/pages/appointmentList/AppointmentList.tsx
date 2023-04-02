import React, { useEffect, useState } from 'react';
import HttpService from "../../service/HttpService";
import { DataGrid, GridColDef } from "@mui/x-data-grid";
import { Link, useParams } from "react-router-dom";
import { DeleteOutline } from "@mui/icons-material";
import { Appointment } from "../../model/Appointment";

const AppointmentList: React.FC = () => {
  let { id } = useParams();
  let sheduleId = parseInt(id!);
  const [data, setData] = useState<Appointment[]>([]);

  useEffect(() => {
    HttpService.getShedule(sheduleId)
      .then(response => setData(response.appointments))
      .catch(error => console.error(error));
  }, []);

  const columns: GridColDef[] = [
    {
      field: 'id',
      headerName: 'ID',
      width: 50,
    },
    {
      field: 'date',
      headerName: 'День приема',
      width: 120,
    },
    {
      field: 'startAppointments',
      headerName: 'Начало приема',
      width: 150,
    },
    {
      field: 'endAppointments',
      headerName: 'Конец приема',
      width: 150,
    },
    {
      field: 'firstName',
      headerName: 'Имя',
      width: 100,
      valueGetter: (params) => params.row.patient.firstName
    },
    {
      field: 'patronymic',
      headerName: 'Отчество',
      width: 140,
      valueGetter: (params) => params.row.patient.patronymic
    },
    {
      field: 'lastName',
      headerName: 'Фамилия',
      width: 120,
      valueGetter: (params) => params.row.patient.lastName
    },
    {
      field: 'action',
      headerName: 'Действие',
      width: 150,
      renderCell: (params) => {
        return (
          <>
            <Link to={`/patient/${params.row.id}`}>
              <button className='editButton'>Изменить</button>
            </Link>
            <DeleteOutline
              className='deleteButton'
              //onClick={() => handleDelete(params.row.id)}
            />
          </>
        );
      },
    },
  ];

  return (
    <div className='appointmentListPage'>
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

export default AppointmentList;