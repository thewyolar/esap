import './details.scss';
import React from "react";
import {Avatar} from "@mui/material";
import {CalendarToday, LocationOn, MailOutline, PermIdentity, PhoneAndroid} from "@mui/icons-material";
import {Patient} from "../../model/Patient";

interface DetailsProps {
  patient: Patient;
}

const Details: React.FC<DetailsProps> = ({patient}) => {
  return (
    <div className="show">
      <div className="top">
        <Avatar alt={`${patient?.firstName} ${patient?.lastName}`} />
        <div className="title">
          <span className="username">{`${patient?.firstName} ${patient?.patronymic} ${patient?.lastName}`}</span>
        </div>
      </div>
      <div className="bottom">
        <span className="title">Общая информация</span>
        <div className="info">
          <CalendarToday className="icon" />
          <span className="infoTitle">{patient?.birthDate}</span>
        </div>
        <span className="title">Контактная информация</span>
        <div className="info">
          <PhoneAndroid className="icon" />
          <span className="infoTitle">{patient?.phoneNumber}</span>
        </div>
        <div className="info">
          <MailOutline className="icon" />
          <span className="infoTitle">{patient?.email}</span>
        </div>
        <div className="info">
          <LocationOn className="icon" />
          <span className="infoTitle">{patient?.address}</span>
        </div>
      </div>
    </div>
  );
};

export default Details;