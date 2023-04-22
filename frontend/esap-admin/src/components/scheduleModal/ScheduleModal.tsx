import {Modal, Box, Grid, Typography, Pagination} from '@mui/material';
import React, {useState} from "react";
import {Schedule} from "../../model/Schedule";
import './schedule.scss';
import ScheduleCard from "./ScheduleCard";
import moment, {Moment} from 'moment';
import 'moment/locale/ru';

interface ScheduleModalProps {
  open: boolean,
  onClose: () => void,
  schedules: Schedule[];
}

const ITEMS_PER_PAGE = 1;

const ScheduleModal: React.FC<ScheduleModalProps> = ({ open, onClose, schedules }) => {
  const [activePage, setActivePage] = useState(0);
  const visibleSchedules = schedules.slice(activePage * ITEMS_PER_PAGE, (activePage + 1) * ITEMS_PER_PAGE);

  const handlePageChange = (pageNumber: number) => {
    setActivePage(pageNumber - 1);
  };

  const getWeekRange = (schedule: Schedule) => {
    const startOfWeek = moment(schedule.date).isoWeekday(1);
    const endOfWeek = startOfWeek.clone().add(6, "days");
    return `${startOfWeek.format("D MMMM")} — ${endOfWeek.format("D MMMM YYYY")}`;
  };

  const getDaysOfWeek = (schedule: Schedule) => {
    const startOfWeek = moment(schedule.date).isoWeekday(1);
    return Array.from(Array(7)).map((_, index) => startOfWeek.clone().add(index, "days"));
  };

  const renderScheduleCard = (schedule: Schedule, day: Moment) => {
    const date = day.format("YYYY-MM-DD");

    return (
      <ScheduleCard
        key={day.toISOString()}
        date={date}
        schedule={schedule}
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
        sx={{
          margin: "auto", padding: 3,
          width: "70%", height: "90%", bgcolor: "background.paper",
          borderRadius: "5px", overflow: "auto", marginTop: "30px"
        }}
      >
        {visibleSchedules.map((schedule) => (
          <div key={schedule.date}>
            <Typography align="center" style={{marginBottom: "12px"}}>
              {getWeekRange(schedule)}
            </Typography>
            <div style={{display: 'flex', justifyContent: 'center', marginBottom: '12px'}}>
              <Pagination count={Math.ceil(schedules.length / ITEMS_PER_PAGE)} page={activePage + 1}
                          onChange={(_, page) => handlePageChange(page)} key={activePage} />
            </div>
            <Grid container spacing={2} columns={14} textAlign="center">
              {getDaysOfWeek(schedule).map((day) => (
                <Grid key={day.toISOString()} item xs={2}>
                  {renderScheduleCard(schedule, day)}
                </Grid>
              ))}
            </Grid>
          </div>
        ))}
      </Box>
    </Modal>
  );
};

export default ScheduleModal;