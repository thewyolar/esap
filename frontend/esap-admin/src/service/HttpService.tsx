import axios, { AxiosInstance } from "axios";
import { ClinicRegistrationDTO } from "../model/dto/ClinicRegistrationDTO";
import { Patient } from "../model/Patient";
import { Schedule } from "../model/Schedule";
import Api from "./auth/Api";

class HttpService {

    private static url = "http://localhost:8080";

    public static async registrationClinics(body: ClinicRegistrationDTO){
        return await Api.post<ClinicRegistrationDTO>(HttpService.url + "/api/clinic", body)
        .then((res) => res.data)
        .catch((error) => error)
    }

    public static async getPatientList() {
        return await Api.get<Patient[]>(HttpService.url + "/api/patient")
          .then((res) => res.data)
          .catch((error) => error)
    }

    public static async getSchedulesList() {
        return await Api.get<Schedule[]>(HttpService.url + "/api/schedule")
          .then((res) => res.data)
          .catch((error) => error)
    }

    public static async getSchedule(id: number) {
        return await Api.get<Schedule>(HttpService.url + `/api/schedule/${id}`)
          .then((res) => res.data)
          .catch((error) => error)
    }

    public static async getDoctorList() {
        return await Api.get<Patient[]>(HttpService.url + "/api/doctor")
          .then((res) => res.data)
          .catch((error) => error)
    }

    public static async getMedicalCard(id: number) {
        return await Api.get<Schedule>(HttpService.url + `/api/medicalCard/patient/${id}`)
          .then((res) => res.data)
          .catch((error) => error)
    }
}

export default HttpService;