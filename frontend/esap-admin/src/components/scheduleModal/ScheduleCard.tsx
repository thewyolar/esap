import React from 'react';
import {Card, CardContent, Typography} from "@mui/material";
import ScheduleTimes from "./ScheduleTimes";
import moment from "moment";
import {Schedule} from "../../model/Schedule";

interface ScheduleCardProps {
  date: string,
  schedule: Schedule;
}

const ScheduleCard: React.FC<ScheduleCardProps> = ({ date, schedule }) => {
  const formattedDate = moment(date).format("ddd, D MMMM");
  const startTimeFormatted = moment('8:30', "HH:mm:ss").format("HH:mm");
  const endTimeFormatted = moment('18:30', "HH:mm:ss").format("HH:mm");
  const times = [];
  let currentTime = moment(startTimeFormatted, "HH:mm");
  while (currentTime.isBefore(moment(endTimeFormatted, "HH:mm"))) {
    times.push(currentTime.format("HH:mm"));
    currentTime.add(30, "minutes");
  }
  times.push(endTimeFormatted);

  return (
    <Card>
      <CardContent style={{paddingBottom: '12px', height: '691px'}}>
        <Typography sx={{ fontSize: 14 }} color="text.primary" gutterBottom>
          {formattedDate}
        </Typography>
        <Typography variant="h5" component="div">
          <ScheduleTimes date={date} times={times} schedule={schedule}/>
        </Typography>
      </CardContent>
    </Card>
  );
};

export default ScheduleCard;