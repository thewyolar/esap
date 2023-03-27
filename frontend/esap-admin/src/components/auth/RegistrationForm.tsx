import React from 'react';
import "./auth.scss";

const RegistrationForm: React.FC = () => {
  return (
    <div className="Auth-form-container">
      <form className="Auth-form">
        <div className="Auth-form-content">
          <h3 className="Auth-form-title">Регистрация</h3>
          <div className="form-group mt-3">
            <label>Имя</label>
            <input
              type="text"
              className="form-control mt-1"
              placeholder="Имя"
            />
          </div>
          <div className="form-group mt-3">
            <label>Фамилия</label>
            <input
              type="text"
              className="form-control mt-1"
              placeholder="Фамилия"
            />
          </div>
          <div className="form-group mt-3">
            <label>Email</label>
            <input
              type="email"
              className="form-control mt-1"
              placeholder="Email"
            />
          </div>
          <div className="form-group mt-3">
            <label>Пароль</label>
            <input
              type="password"
              className="form-control mt-1"
              placeholder="Пароль"
            />
          </div>
          <div className="d-grid gap-2 my-3">
            <button type="submit" className="btn btn-primary">
              Зарегистрироваться
            </button>
          </div>
        </div>
      </form>
    </div>
  );
};

export default RegistrationForm;