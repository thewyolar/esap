import React, { useState } from "react";
import { InfoLogin } from "../../model/auth/InfoLogin";
import AuthService from "../../service/auth/AuthService";
import { TokenStorageService } from "../../service/auth/TokenStorageService";
import "./auth.scss";

const LoginForm: React.FC = () => {
  
  const tokenStorageService: TokenStorageService = new TokenStorageService
  const roles: Array<string> = [];
  const [login, setLogin] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState<string | null>(null);

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const infoLogin: InfoLogin = {
      login: login,
      password: password
    };
    AuthService.attemptAuth(infoLogin)
    .then(response => {
      tokenStorageService.saveToken(response.jwt);
      tokenStorageService.saveLogin(login);
      window.location.reload();
    })
    .catch(error => {
      setError(error.response.data.message);
    });

  };

  return (
    <div className="Auth-form-container">
      {error && <div className="alert alert-danger">{error}</div>}
      <form className="Auth-form" onSubmit={handleSubmit}>
        <div className="Auth-form-content">
          <h3 className="Auth-form-title">Вход в систему</h3>
          <div className="form-group mt-3">
            <label>Логин</label>
            <input
              id="login" type="text" className="form-control mt-1" placeholder="Логин" value={login}
              onChange={(event) => setLogin(event.target.value)} required
            />
          </div>
          <div className="form-group mt-3">
            <label>Пароль</label>
            <input
              id="password" type="password" className="form-control mt-1" placeholder="Пароль" value={password}
              onChange={(event) => setPassword(event.target.value)} required
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
