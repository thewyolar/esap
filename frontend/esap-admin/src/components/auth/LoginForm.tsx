import React from "react";
import "./auth.scss";

const LoginForm: React.FC = () => {
  return (
    <div className="Auth-form-container">
      <form className="Auth-form">
        <div className="Auth-form-content">
          <h3 className="Auth-form-title">Вход в систему</h3>
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
          <div className="d-grid gap-2 mt-3">
            <button type="submit" className="btn btn-primary">
              Войти
            </button>
          </div>
          <p className="forgot-password text-right mt-2">
            Забыли <a href="login#">пароль?</a>
          </p>
        </div>
      </form>
    </div>
  );
};

export default LoginForm;
