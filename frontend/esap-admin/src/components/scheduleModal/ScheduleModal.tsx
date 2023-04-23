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
  const sortedSchedules = schedules.sort((a, b) => moment(a.date).diff(moment(b.date)));
  const visibleSchedules = sortedSchedules.slice(activePage * ITEMS_PER_PAGE, (activePage + 1) * ITEMS_PER_PAGE);

  const handlePageChange = (pageNumber: number) => {
    setActivePage(pageNumber - 1);
  };

  const getWeekRange = (schedule: Schedule) => {
    const startOfWeek = moment(schedule.date).isoWeekday(1);
    const endOfWeek = startOfWeek.clone().add(6, "days");
    return `${startOfWeek.format("D MMMM")} — ${endOfWeek.format("D MMMM YYYY")}`;
  };

  const getDays = (schedules: Schedule[]) => {
    const x = moment(schedules[0].date).isoWeekday(1);
    // let startOfWeek = moment(schedules[0].date).isoWeekday();
    let days = Array.from(Array(7)).map((_, index) => {
      const day = x.clone().add(index, 'days');
      return { date: day.toISOString() } as Schedule;
    });
    // let days: Schedule[] = Array(7).fill({}).map((index) => ({ date: x.clone().add().isoWeekday(index + 1).toISOString()}) as Schedule);
    schedules.map(schedule => {
      let startOfWeek = moment(schedule.date).isoWeekday();
      if (moment(schedule.date).isoWeek() === moment(days[0].date).isoWeek()) {
        console.log(schedule);
        console.log(startOfWeek);
        days[startOfWeek - 1] = schedule;
      }
      console.log(days);
      console.log(startOfWeek);
    });

    return Array(days);
  }

  console.log(visibleSchedules)

  // const getDaysOfWeek = (schedules: Schedule[]) => {
  //   const startOfWeek = moment(schedule.date).isoWeekday(1);
  //   return Array.from(Array(7)).map((_, index) => startOfWeek.clone().add(index, "days"));
  // };

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
        {getDays(sortedSchedules).map((week) => (
          <div>
            <Typography align="center" style={{marginBottom: "12px"}}>
              {/*{getWeekRange(schedule)}*/}
            </Typography>
            <div style={{display: 'flex', justifyContent: 'center', marginBottom: '12px'}}>
              <Pagination count={Math.ceil(schedules.length / ITEMS_PER_PAGE)} page={activePage + 1}
                          onChange={(_, page) => handlePageChange(page)} key={activePage} />
            </div>
            <Grid container spacing={2} columns={14} textAlign="center">
              {week.map((day) => (
                <Grid key={day.id} item xs={2}>
                  {renderScheduleCard(day, moment(day.date))}
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