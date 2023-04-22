import React from "react";
import './schedule.scss';
import DoNotDisturbIcon from '@mui/icons-material/DoNotDisturb';
import moment from "moment";
import {Schedule} from "../../model/Schedule";

interface ScheduleTimesProps {
  date: string,
  times: string[],
  schedule: Schedule;
}

const ScheduleTimes: React.FC<ScheduleTimesProps> = ({ date, times, schedule }) => {
  const hasAppointment = (time: string) => {
    const currentTime = moment(time, "HH:mm");
    if (currentTime.isBefore(moment(schedule.startDoctorAppointment, "HH:mm")) || currentTime.isAfter(moment(schedule.endDoctorAppointment, "HH:mm"))) {
      return 'schedule_time';
    } else if (
      currentTime.isBetween(moment(schedule.startDoctorAppointment, "HH:mm"), moment(schedule.endDoctorAppointment, "HH:mm"), null, "[]") &&
      schedule.appointments.some(
        appointment =>
          appointment.date === date &&
          appointment.startAppointments === moment(time, "HH:mm").format("HH:mm:ss")
      )) {
      return 'schedule_time schedule_time-disable';
    }
    return 'schedule_time schedule_time-active';
  };

  return (
    <div className="schedule_times">
      {schedule.date === date ? (
        times.map((time) => (
          <div className={`${hasAppointment(time)}`} key={time}>
            {time}
          </div>
        ))
      ) : (
        <div className='schedule_times-empty'>
          <DoNotDisturbIcon style={{color: '#aaaaaa', fontSize: '16px'}} />
          <div className='schedule_times-empty-text'>
            В этот день приема нет
          </div>
        </div>
      )}
    </div>
  );
};

export default ScheduleTimes;