import React, { useEffect, useState } from "react";
import HttpService from "../../service/HttpService";
import { DataGrid, GridColDef, ruRU } from "@mui/x-data-grid";
import { Link } from "react-router-dom";
import { DeleteOutline } from "@mui/icons-material";
import { Doctor } from "../../model/Doctor";
import ScheduleModal from "../../components/scheduleModal/ScheduleModal";
import "./doctorList.scss";
import {
  IconButton,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
} from "@mui/material";
import CalendarMonthIcon from "@mui/icons-material/CalendarMonth";
import EditIcon from "@mui/icons-material/Edit";

const DoctorList: React.FC = () => {
  const [data, setData] = useState<Doctor[]>([]);
  const [open, setOpen] = useState(false);
  const [selectedDoctor, setSelectedDoctor] = useState<Doctor>();
  const [maxPage, setMaxPage] = useState<number>(0);
  const [page, setPage] = useState<number>(0);
  const [pageSize, setPageSize] = useState<number>(10);

  useEffect(() => {
    HttpService.getDoctorList(page)
      .then((response) => setData(response))
      .catch((error) => console.error(error));
    if (maxPage == 0) {
      HttpService.getDoctorCount()
        .then((response) => setMaxPage(response))
        .catch((error) => console.error(error));
    }
  }, [page]);


  const handlePageChange = (newPage: number) => {
    if(newPage < 0){
      return;
    }
    setData([]);
    setPage(newPage);
  };

  const handlePageSizeChange = (newPageSize: number) => {
    setPageSize(newPageSize);
  };

  const handleOpen = (doctor: Doctor) => {
    setSelectedDoctor(doctor);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleDelete = (id: number) => {
    setData(data.filter((item) => item.id !== id));
  };

  return (
    <div className="doctorListPage">
      <TableContainer sx={{ minHeight: '100%'}}>
        <Table sx={{ minWidth: 650, minHeight: 600}} aria-label="simple table">
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
                  {row.specialization}
                </TableCell>
                <TableCell sx={{ padding: "5px" }} align="center">
                  <>
                    <Link to={`/doctor/${row.id}`}>
                      <IconButton
                        color="primary"
                        aria-label="edit doctor"
                        component="label"
                      >
                        <EditIcon />
                      </IconButton>
                    </Link>
                    <Link to="#">
                      <IconButton
                        color="primary"
                        aria-label="doctor schedule"
                        component="label"
                        onClick={() => handleOpen(row)}
                      >
                        <CalendarMonthIcon />
                      </IconButton>
                    </Link>
                    <IconButton
                      color="primary"
                      aria-label="delete doctor"
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
      {selectedDoctor && (
        <ScheduleModal
          doctor={selectedDoctor}
          schedules={selectedDoctor.schedules}
          open={open}
          onClose={handleClose}
        />
      )}
    </div>
  );
};

export default DoctorList;
