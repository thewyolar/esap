import axios from "axios";
import { InfoLogin } from "../../model/auth/InfoLogin";
import { JwtToken } from "../../model/auth/JwtToken";

class AuthService {
  constructor() {}

  private static loginUrl = "http://localhost:8080/api/auth/login";

  public static async attemptAuth(credentials: InfoLogin) {
    console.log(credentials);
    return axios.post<JwtToken>(this.loginUrl, credentials)
      .then((res) => res.data)
      .catch((error) => {throw error});
  }
}

export default AuthService;
