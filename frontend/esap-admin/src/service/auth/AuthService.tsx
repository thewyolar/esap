import {InfoLogin} from "../../model/auth/InfoLogin";
import {AuthInfo} from "../../model/auth/AuthInfo";
import {ClinicRegistrationDTO} from "../../model/dto/ClinicRegistrationDTO";
import {DoctorRegistrationDTO} from "../../model/dto/DoctorRegistrationDTO";
import Api from "./Api";

class AuthService {
  private static BASE_URL = "http://localhost:8080/api/auth";

  public static async attemptAuth(credentials: InfoLogin) {
    return await Api.post<AuthInfo>(this.BASE_URL + "/login", credentials)
      .then((res) => res.data)
      .catch((error) => {throw error});
  }

  public static async resetPassword(credentials: InfoLogin) {
    return await Api.post<InfoLogin>(this.BASE_URL + "/password/reset", credentials)
      .then((res) => res.data)
      .catch((error) => {throw error});
  }

  public static async registrationClinics(body: ClinicRegistrationDTO) {
    return await Api.post<InfoLogin>(this.BASE_URL + "/registration/clinic", body)
      .then((res) => res.data)
      .catch((error) => {
        console.log(error.request)
        throw error;
      });
  }

  public static async registrationDoctor(doctor: DoctorRegistrationDTO) {
    return await Api.post<InfoLogin>(this.BASE_URL + "/registration/doctor", doctor)
      .then((res) => res.data)
      .catch((error) => {throw error});
  }

  public static async getAllRoles() {
    return await Api.get<string[]>(this.BASE_URL + "/roles")
      .then((res) => res.data)
      .catch((error) => {throw error});
  }
}

export default AuthService;
