import "./featuredInfo.scss";
import HttpService from "../../service/HttpService";
import {useEffect, useState} from "react";
import {Doctor} from "../../model/Doctor";
import {Link} from "react-router-dom";

const FeaturedInfo = () => {
  const [doctor, setDoctor] = useState<Doctor>();
  const [error, setError] = useState<Doctor>();

  useEffect(() => {
    HttpService.getDoctor()
      .then((response) => {
        setDoctor(response);
      })
      .catch((error) => setError(error));
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