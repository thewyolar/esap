import React, {useEffect, useState} from 'react';
import {Grid, Card, CardContent, Typography, List, ListItem, ListItemAvatar, Avatar, ListItemText} from "@mui/material";
import {useParams} from 'react-router-dom';
import {Schedule} from '../../model/Schedule';
import HttpService from '../../service/HttpService';
import {Appointment} from "../../model/Appointment";
import { Link } from 'react-router-dom';

const Queue: React.FC = () => {
  const { scheduleId } = useParams<{ scheduleId: string }>();
  const [schedule, setSchedule] = useState<Schedule>();

  useEffect(() => {
    if (scheduleId) {
      HttpService.getScheduleByIdAndDoctor(parseInt(scheduleId))
        .then((response) => setSchedule(response))
        .catch((error) => console.error(error));
    }
  }, [scheduleId]);

  return (
    <Grid container className="queue" spacing={2} justifyContent="center">
      <Grid item xs={12} sm={8} md={6}>
        <Card raised>
          <CardContent>
            <Typography variant="h5" component="h2" sx={{ mb: 2 }}>
              Электронная очередь
            </Typography>
            <List sx={{ display: 'flex', flexDirection: 'column'}}>
              {schedule?.appointments.map((appointment: Appointment, index: number) => (
                <ListItem key={index} disablePadding>
                  <ListItemAvatar>
                    <Avatar sx={{ bgcolor: 'primary.main' }}>{index + 1}</Avatar>
                  </ListItemAvatar>
                  <ListItemText
                    primary={`${appointment.patient.lastName} ${appointment.patient.firstName}`}
                    secondary={`Время записи: ${appointment.startAppointments}`}
                  />
                  <Link to={`/editingMedicalCard/${appointment.patient.id}`}>Карта</Link>
                </ListItem>
              ))}
            </List>
          </CardContent>
        </Card>
      </Grid>
    </Grid>
  );
};

export default Queue;