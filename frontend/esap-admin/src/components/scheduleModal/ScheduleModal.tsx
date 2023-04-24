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

const WEEKS_PER_PAGE = 1;

const ScheduleModal: React.FC<ScheduleModalProps> = ({ open, onClose, schedules }) => {
  const sortedSchedules = schedules.sort((a, b) => moment(a.date).diff(moment(b.date)));

  const handlePageChange = (pageNumber: number) => {
    setActivePage(pageNumber - 1);
  };

  const getWeekRange = (schedule: Schedule) => {
    const startOfWeek = moment(schedule.date).isoWeekday(1);
    const endOfWeek = startOfWeek.clone().add(6, "days");
    return `${startOfWeek.format("D MMMM")} — ${endOfWeek.format("D MMMM YYYY")}`;
  };

  const getWeeks = (schedules: Schedule[]) => {
    const weeks: Schedule[][] = [];
    const firstDayOfWeek = moment(schedules[0].date).isoWeekday(1);
    const lastDayOfWeek = moment(schedules[schedules.length - 1].date).isoWeekday(7);
    let week: Schedule[] = [];
    let currentDay = firstDayOfWeek;

    while (currentDay <= lastDayOfWeek) {
      const schedule = schedules.find(s => moment(s.date).isSame(currentDay, 'day'));

      if (schedule) {
        week.push(schedule);
      } else {
        week.push({ date: currentDay.toISOString() } as Schedule);
      }

      if (currentDay.isoWeekday() === 7) {
        weeks.push(week);
        week = [];
      }
      currentDay = currentDay.add(1, 'day');
    }

    if (week.length > 0) {
      weeks.push(week);
    }

    return weeks;
  }

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

  const weeks = getWeeks(sortedSchedules);
  const totalPages = Math.ceil(weeks.length / WEEKS_PER_PAGE);
  const currentWeekIndex = weeks.findIndex((week) =>
    week.some((schedule) => moment(schedule.date).isSame(moment(), 'week'))
  );
  const defaultPage = currentWeekIndex === -1 ? 0 : Math.floor(currentWeekIndex / WEEKS_PER_PAGE);
  const [activePage, setActivePage] = useState(defaultPage);
  const visibleSchedules = weeks.slice(activePage * WEEKS_PER_PAGE, (activePage + 1) * WEEKS_PER_PAGE);

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
        {visibleSchedules.map((week) => (
          <div key={getWeekRange(week[0])}>
            <Typography align="center" style={{marginBottom: "12px"}}>
              {getWeekRange(week[0])}
            </Typography>
            {totalPages > 1 && (
              <div style={{display: 'flex', justifyContent: 'center', marginBottom: '12px'}}>
                <Pagination
                  count={totalPages}
                  page={activePage + 1}
                  onChange={(_, page) => handlePageChange(page)}
                  siblingCount={0} boundaryCount={1}
                  key={activePage}
                />
              </div>
            )}
            <Grid container spacing={2} columns={14} textAlign="center">
              {week.map((day) => (
                <Grid key={day.date} item xs={2}>
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