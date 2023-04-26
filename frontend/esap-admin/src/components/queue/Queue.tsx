import React, {useEffect, useState} from 'react';
import {Grid, Card, CardContent, Typography, List, ListItem, ListItemAvatar, Avatar, ListItemText} from "@mui/material";
import {useParams} from 'react-router-dom';
import {Schedule} from '../../model/Schedule';
import HttpService from '../../service/HttpService';

const Queue: React.FC = () => {
  let { scheduleId } = useParams();
  let scheduleIdInt = parseInt(scheduleId!);
  const [schedule, setData] = useState<Schedule>();

  useEffect(() => {
    if (scheduleIdInt) { // Добавлено условие
      HttpService.getScheduleByIdAndDoctor(scheduleIdInt)
        .then((response) => setData(response))
        .catch((error) => console.error(error));
    }
  }, [scheduleIdInt]);

  return (
    <Grid className="queue" container spacing={0}>
      <Grid item xs={12} sm={6} md={4}>
        <Card>
          <CardContent>
            <Typography variant="h5" component="h2">
              Электронная очередь
            </Typography>
            <List>
              {schedule?.appointments.map((item, index) => (
                <ListItem key={index}>
                  <ListItemAvatar>
                    <Avatar>{index + 1}</Avatar>
                  </ListItemAvatar>
                  <ListItemText primary={item.patient.firstName + " " + item.patient.lastName} secondary={`Время записи: ${item.startAppointments}`} />
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