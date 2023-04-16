import {Modal, Box, Grid, Typography} from '@mui/material';
import React from "react";
import {Schedule} from "../../model/Schedule";
import './schedule.scss';
import ScheduleCard from "./ScheduleCard";
import moment, {Moment} from 'moment';
import 'moment/locale/ru';

interface ScheduleModalProps {
  open: boolean,
  onClose: () => void,
  schedule: Schedule
}

const ScheduleModal: React.FC<ScheduleModalProps> = ({ open, onClose, schedule }) => {
  const startOfWeek = moment(schedule.date).isoWeekday(1);
  const endOfWeek = startOfWeek.clone().add(6, "days");
  const weekRange = `${startOfWeek.format("D MMMM")} — ${endOfWeek.format("D MMMM YYYY")}`;
  const daysOfWeek = Array.from(Array(7)).map((_, index) => startOfWeek.clone().add(index, "days"));

  const renderScheduleCard = (day: Moment) => {
    const date = day.format("YYYY-MM-DD");
    const { startDoctorAppointment, endDoctorAppointment, appointments } = schedule;
    return (
      <ScheduleCard
        key={day.toISOString()}
        date={date}
        startTime={startDoctorAppointment}
        endTime={endDoctorAppointment}
        appointments={appointments}
      />
    );
  };

  return (
    <Modal
      open={open}
      onClose={onClose}
      aria-labelledby="modal-modal-title"
      aria-describedby="modal-modal-description"
    >
      <Box
        sx={{margin: "auto", mt: 12, p: 3,
          width: "70%", height: "80%", bgcolor: "background.paper",
          borderRadius: "5px", overflow: "auto",
        }}
      >
        <Typography id="modal-modal-title" align="center" style={{ marginBottom: "10px" }}>
          {weekRange}
        </Typography>
        <Grid container spacing={2} columns={14} textAlign="center">
          {daysOfWeek.map((day) => (
            <Grid key={day.toISOString()} item xs={2}>
              {renderScheduleCard(day)}
            </Grid>
          ))}
        </Grid>
      </Box>
    </Modal>
  );
};

export default ScheduleModal;
