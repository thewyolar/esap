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
  const startTimeFormatted = moment(startTime, "HH:mm:ss").format("HH:mm");
  const endTimeFormatted = moment(endTime, "HH:mm:ss").format("HH:mm");
  const times = [];
  let currentTime = moment(startTimeFormatted, "HH:mm");
  while (currentTime.isBefore(moment(endTimeFormatted, "HH:mm"))) {
    times.push(currentTime.format("HH:mm"));
    currentTime.add(30, "minutes");
  }
  times.push(endTimeFormatted);

  return (
    <Card>
      <CardContent>
        <Typography sx={{ fontSize: 14 }} color="text.primary" gutterBottom>
          {formattedDate}
        </Typography>
        <Typography variant="h5" component="div">
          <ScheduleTimes date={date} times={times} appointments={appointments} />
        </Typography>
      </CardContent>
    </Card>
  );
};

export default ScheduleCard;