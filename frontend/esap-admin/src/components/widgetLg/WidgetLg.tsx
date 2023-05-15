import Button from './button/Button'
import './widgetLg.scss'
import React, {useEffect, useState} from "react";
import {Patient} from "../../model/Patient";
import HttpService from "../../service/HttpService";
import {Appointment} from "../../model/Appointment";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";

const WidgetLg: React.FC = () => {
  const [appointments, setAppointments] = useState<Appointment[]>([]);

  useEffect(() => {
    HttpService.getLatestDoctorAppointments()
      .then((response) => {
        setAppointments(response);
      });
  }, []);

  const formatTime = (time: string) => {
    return new Date(`1970-01-01T${time}`).toLocaleTimeString([], {
      hour: '2-digit',
      minute: '2-digit',
      hour12: false,
    });
  };

  return (
    <div className='widgetLg'>
      <h3 className="title">Последние приемы</h3>
      <TableContainer>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell sx={{fontWeight: 'bold'}}>Пациент</TableCell>
              <TableCell sx={{fontWeight: 'bold'}}>Дата</TableCell>
              <TableCell sx={{fontWeight: 'bold'}}>Время</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {appointments.map((appointment) => (
              <TableRow key={appointment.id}>
                <TableCell>{`${appointment.patient.lastName} ${appointment.patient.firstName} ${appointment.patient.patronymic}`}</TableCell>
                <TableCell>{appointment.date}</TableCell>
                <TableCell>{`${formatTime(appointment.startAppointments)}-${formatTime(appointment.endAppointments)}`}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default WidgetLg;
