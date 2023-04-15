import './sheduleList.scss';
import {DataGrid, GridColDef, ruRU} from '@mui/x-data-grid';
import React, {useEffect, useState} from 'react';
import {DeleteOutline} from '@mui/icons-material';
import HttpService from "../../service/HttpService";
import {Schedule} from "../../model/Schedule";
import ScheduleModal from "../../components/scheduleModal/ScheduleModal";

const ScheduleList: React.FC = () => {
  const [data, setData] = useState<Schedule[]>([]);
  const [open, setOpen] = useState(false);
  const [selectedSchedule, setSelectedSchedule] = useState<Schedule>();
  const handleOpen = (schedule: Schedule) => {
    setSelectedSchedule(schedule);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  useEffect(() => {
    HttpService.getSchedulesList()
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
      field: 'doctor.firstName',
      headerName: 'Имя',
      width: 100,
      valueGetter: (params) => params.row.doctor.firstName
    },
    {
      field: 'doctor.patronymic',
      headerName: 'Отчество',
      width: 140,
      valueGetter: (params) => params.row.doctor.patronymic
    },
    {
      field: 'doctor.lastName',
      headerName: 'Фамилия',
      width: 120,
      valueGetter: (params) => params.row.doctor.lastName
    },
    {
      field: 'date',
      headerName: 'Дата приема',
      width: 120,
    },
    {
      field: 'startDoctorAppointment',
      headerName: 'Начало рабочего дня',
      width: 150,
    },
    {
      field: 'endDoctorAppointment',
      headerName: 'Конец рабочего дня',
      width: 170,
    },
    {
      field: 'action',
      headerName: 'Действие',
      width: 170,
      renderCell: (params) => {
        return (
          <>
            <button className='editButton' onClick={() => handleOpen(params.row)}>Изменить</button>
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
    <div className='scheduleListPage'>
      <DataGrid
        localeText={ruRU.components.MuiDataGrid.defaultProps.localeText}
        rows={data}
        disableSelectionOnClick
        columns={columns}
        pageSize={13}
        rowsPerPageOptions={[5]}
        checkboxSelection
      />
      {selectedSchedule && (
        <ScheduleModal
          schedule={selectedSchedule}
          open={open}
          onClose={handleClose}
        />
      )}
    </div>
  );
};

export default ScheduleList;
