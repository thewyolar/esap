import { InfoLogin } from "../../model/auth/InfoLogin";
import { JwtToken } from "../../model/auth/JwtToken";
import {ClinicRegistrationDTO} from "../../model/dto/ClinicRegistrationDTO";
import Api from "./Api";

class AuthService {
  private static url = "http://localhost:8080/api/auth";

  public static async attemptAuth(credentials: InfoLogin) {
    return Api.post<JwtToken>(this.url + "/login", credentials)
      .then((res) => res.data)
      .catch((error) => {throw error});
  }

  public static async resetPassword(credentials: InfoLogin) {
    return Api.post<InfoLogin>(this.url + "/password/reset", credentials)
      .then((res) => res.data)
      .catch((error) => {throw error});
  }
}

export default AuthService;
