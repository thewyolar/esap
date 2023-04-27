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
    });

  return (
    <div className="featuredInfo">
      <Link
        className="item"
        to={`queue/${schedulesForToday && schedulesForToday[0]?.id}`}
      >
        <span className="title">Записей на сегодня</span>
        <div>
          <span className="money">
            {!schedulesForToday || schedulesForToday.length === 0
              ? 0
              : schedulesForToday[0].appointments.length}
          </span>
        </div>
      </Link>

      <div className="item">
        <span className="title">Lorem ipsum</span>
        <div>
          <span className="money">Lorem ipsum</span>
          <span className="moneyRate"></span>
        </div>
      </div>

      <div className="item">
        <span className="title">Lorem ipsum</span>
        <div>
          <span className="money">Lorem ipsum</span>
          <span className="moneyRate"></span>
        </div>
        <span className="sub">Lorem ipsum</span>
      </div>
    </div>
  );
};

export default FeaturedInfo;