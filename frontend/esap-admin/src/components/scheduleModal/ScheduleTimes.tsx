import React from "react";
import './schedule.scss';
import {Appointment} from "../../model/Appointment";
import moment from "moment";

interface ScheduleTimesProps {
  date: string,
  times: string[],
  appointments: Appointment[]
}

const ScheduleTimes: React.FC<ScheduleTimesProps> = ({ date, times, appointments }) => {
  const hasAppointment = (time: string) => {
    return appointments.some((appointment) => {
      return appointment.date === date && appointment.startAppointments === moment(time, 'HH:mm').format('HH:mm:ss');
    });
  };

  return (
    <div className="schedule_times">
      {times.map((time) => (
        <div className={`schedule_time ${hasAppointment(time) ? "" : "schedule_time-active"}`} key={time}>
          {time}
        </div>
      ))}
    </div>
  );
};

export default ScheduleTimes;

