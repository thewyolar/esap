import {Doctor} from "../model/Doctor";
import {MedicalCard} from "../model/MedicalCard";
import {Patient} from "../model/Patient";
import {Schedule} from "../model/Schedule";
import Api from "./auth/Api";
import {AppointmentDTO} from "../model/dto/AppointmentDTO";
import { MedicalRecord } from "../model/MedicalRecord";
import {ScheduleDTO} from "../model/dto/ScheduleDTO";

class HttpService {
  private static url = "http://localhost:8080";

  public static async getPatientList() {
    return await Api.get<Patient[]>(HttpService.url + "/api/patient")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getLatestPatients() {
    return await Api.get<Patient[]>(HttpService.url + "/api/patient/latest")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getPatientCount() {
    return await Api.get<number>(HttpService.url + "/api/patient/count")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async searchPatientList(firstName: string, patronymic: string, lastName: string) {
    return await Api.get<Patient[]>(HttpService.url + "/api/patient", {
      params: {
        firstName: firstName,
        patronymic: patronymic,
        lastName: lastName
      }
    })
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getDoctorList() {
    return await Api.get<Doctor[]>(HttpService.url + "/api/doctor")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getDoctorCount() {
    return await Api.get<number>(HttpService.url + "/api/doctor/count")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getMedicalCard(id: number) {
    return await Api.get<MedicalCard>(
      HttpService.url + `/api/medicalCard/patient/${id}`
    )
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async saveMedicalRecord(id: number, body: MedicalRecord) {
    return await Api.post<MedicalCard>(
      HttpService.url + `/api/medicalCard/patient/${id}`, body
    )
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getPatient(id: number) {
    return await Api.get<Patient>(HttpService.url + `/api/patient/${id}`)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async scheduleByDay(date: string) {
    return await Api.get<Schedule>(HttpService.url + `/api/schedule/day`, {
      params: { date: date },
    })
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getDoctor() {
    return await Api.get<Doctor>(HttpService.url + `/api/doctor/home`)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getScheduleByIdAndDoctor(id: number) {
    return await Api.get<Schedule>(HttpService.url + `/api/schedule/${id}`)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async addDoctorSchedule(body: ScheduleDTO) {
    return await Api.post<AppointmentDTO>(HttpService.url + `/api/schedule`, body)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async addDoctorAppointment(id: number, body: AppointmentDTO) {
    return await Api.post<AppointmentDTO>(HttpService.url + `/api/schedule/${id}/appointment`, body)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }
}

export default HttpService;
