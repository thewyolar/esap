import React from 'react';
import {Card, CardContent, Typography} from "@mui/material";
import ScheduleTimes from "./ScheduleTimes";
import {Appointment} from "../../model/Appointment";
import moment from "moment";

interface ScheduleCardProps {
  date: string,
  startTime: string,
  endTime: string,
  appointments: Appointment[]
}

const ScheduleCard: React.FC<ScheduleCardProps> = ({ date, startTime, endTime, appointments }) => {
  const formattedDate = moment(date).format("ddd, D MMMM");
  const times = [];
  let currentTime = startTime;
  while (currentTime < endTime) {
    times.push(currentTime);
    const [hour, minute] = currentTime.split(':');
    const dateObj = new Date();
    dateObj.setHours(Number(hour));
    dateObj.setMinutes(Number(minute));
    dateObj.setMinutes(dateObj.getMinutes() + 30);
    currentTime = dateObj.toTimeString().slice(0, 5);
  }
  times.push(endTime);

  return (
    <Card>
      <CardContent>
        <Typography sx={{ fontSize: 14 }} color="text.primary" gutterBottom>
          {formattedDate}
        </Typography>
        <Typography variant="h5" component="div">
          <ScheduleTimes date={date} times={times}  appointments={appointments} />
        </Typography>
      </CardContent>
    </Card>
  );
};

export default ScheduleCard;