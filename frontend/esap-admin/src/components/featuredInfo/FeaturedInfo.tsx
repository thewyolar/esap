import "./featuredInfo.scss";
import HttpService from "../../service/HttpService";
import React, {useEffect, useState} from "react";
import {Doctor} from "../../model/Doctor";
import {Link} from "react-router-dom";
import FeaturedInfoItem from "./FeaturedInfoItem";

const FeaturedInfo: React.FC = () => {
  const [doctor, setDoctor] = useState<Doctor>();
  const [error, setError] = useState<Doctor>();
  const [doctorsCount, setDoctorsCount] = useState(0);
  const [patientsCount, setPatientsCount] = useState(0);

  useEffect(() => {
    HttpService.getDoctor()
      .then((response) => {
        setDoctor(response);
      })
      .catch((error) => setError(error));
  }, []);

  useEffect(() => {
    HttpService.getDoctorCount()
      .then((response) => {
      setDoctorsCount(response);
    });
  }, []);

  useEffect(() => {
    HttpService.getPatientCount()
      .then((response) => {
        setPatientsCount(response);
      });
  }, []);

  const schedulesForToday =
    doctor &&
    doctor.schedules.filter((schedule) => {
      const today = new Date();
      const scheduleDate = new Date(schedule.date);
      return (
        scheduleDate.getFullYear() === today.getFullYear() &&
        scheduleDate.getMonth() === today.getMonth() &&
        scheduleDate.getDate() === today.getDate()
      );
    }
  );

  return (
    <div className="featuredInfo">

      {doctor && (
        <>
          <FeaturedInfoItem
            title={doctor.clinic.name}
            description={doctor.clinic.address}
          />
          <Link to={`queue/${schedulesForToday && schedulesForToday[0]?.id}`}>
            <FeaturedInfoItem
              title={"Записей на сегодня"}
              description={!schedulesForToday || schedulesForToday.length === 0
                ? 0
                : schedulesForToday[0].appointments.length}
            />
          </Link>
          <FeaturedInfoItem
            title={"Общее кол-во врачей"}
            description={doctorsCount}
          />
          <FeaturedInfoItem
            title={"Общее кол-во пациентов"}
            description={patientsCount}
          />
        </>
      )}
    </div>
  );
};

export default FeaturedInfo;