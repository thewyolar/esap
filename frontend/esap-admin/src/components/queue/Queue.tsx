import React, {useEffect, useState} from 'react';
import {Link, useParams} from 'react-router-dom';
import {Schedule} from '../../model/Schedule';
import HttpService from '../../service/HttpService';
import {Appointment} from "../../model/Appointment";
import {Timeline, TimelineConnector, TimelineContent, TimelineDot, TimelineItem, TimelineOppositeContent, timelineOppositeContentClasses, TimelineSeparator} from "@mui/lab";
import {Grid, Typography} from "@mui/material";
import moment from "moment";

const Queue: React.FC = () => {
  const { scheduleId } = useParams<{ scheduleId: string }>();
  const [schedule, setSchedule] = useState<Schedule>();

  useEffect(() => {
    if (scheduleId) {
      HttpService.getScheduleById(parseInt(scheduleId))
        .then((response) => setSchedule(response))
        .catch((error) => console.error(error));
    }
  }, [scheduleId]);

  const formatTime = (timeString: string) => {
    const date = new Date(timeString);
    return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' });
  };

  return (
    <Grid container>
      <Grid item xs={12}>
        <Typography variant="h6" component="h2" sx={{ m: 2 }}>
          Журнал приемов
        </Typography>
        <Timeline
          sx={{
            [`& .${timelineOppositeContentClasses.root}`]: {
              flex: 'unset',
              paddingRight: '16px',
            },
          }}
        >
          {schedule?.appointments.map((appointment: Appointment, index: number) => (
            <TimelineItem key={index}>
              <TimelineOppositeContent
                sx={{ flex: 0.2 }}
                align="right"
                variant="body2"
                color="text.secondary"
              >
                {moment(appointment.startAppointments, "HH:mm:ss").format("HH:mm")}
              </TimelineOppositeContent>
              <TimelineSeparator>
                <TimelineDot variant="outlined" sx={{ margin: '0' }} />
                {index !== schedule.appointments.length - 1 && <TimelineConnector />}
              </TimelineSeparator>
              <TimelineContent sx={{ px: 2 }}>
                <Typography variant="h6" component="span" sx={{ fontSize: '16px', fontWeight: 'bold' }} color="primary">
                  {`${appointment.patient.lastName} ${appointment.patient.firstName}`}
                </Typography>
                <Link to={`/editingMedicalCard/${appointment.patient.id}`}>
                  <Typography variant="body2" color="text.secondary">
                    Мед. карта
                  </Typography>
                </Link>
              </TimelineContent>
            </TimelineItem>
          ))}
        </Timeline>
      </Grid>
    </Grid>
  );
};

export default Queue;