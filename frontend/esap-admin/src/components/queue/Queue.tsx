import React from 'react';
import { Grid, Card, CardContent, Typography, List, ListItem, ListItemAvatar, Avatar, ListItemText } from "@mui/material";
import { QueueItem } from "../../model/QueueItem";

const queue = [
  { name: 'Иван Иванов', appointmentTime: '10:00' },
  { name: 'Петр Петров', appointmentTime: '10:30' },
  { name: 'Сидор Сидоров', appointmentTime: '11:00' },
];

const Queue: React.FC = () => {
  return (
    <Grid container spacing={2}>
      <Grid item xs={12} sm={6} md={4}>
        <Card>
          <CardContent>
            <Typography variant="h5" component="h2">
              Электронная очередь
            </Typography>
            <List>
              {queue.map((item, index) => (
                <ListItem key={index}>
                  <ListItemAvatar>
                    <Avatar>{index + 1}</Avatar>
                  </ListItemAvatar>
                  <ListItemText primary={item.name} secondary={`Время записи: ${item.appointmentTime}`} />
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