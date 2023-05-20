import {Doctor} from "../model/Doctor";
import {MedicalCard} from "../model/MedicalCard";
import {Patient} from "../model/Patient";
import {Schedule} from "../model/Schedule";
import Api from "./auth/Api";
import {AppointmentDTO} from "../model/dto/AppointmentDTO";
import { MedicalRecord } from "../model/MedicalRecord";
import {ScheduleDTO} from "../model/dto/ScheduleDTO";
import {Appointment} from "../model/Appointment";
import {AppointmentsCountByDayDTO} from "../model/dto/AppointmentsCountByDayDTO";
import {PatientStatisticsByGenderDTO} from "../model/dto/PatientStatisticsByGenderDTO";
import {PatientStatisticsByAgeDTO} from "../model/dto/PatientStatisticsByAgeDTO";

class HttpService {
  private static BASE_URL = "http://localhost:8080";
  private static API_PATIENT = "/api/patient";
  private static API_DOCTOR = "/api/doctor";
  private static API_MEDICAL_CARD = "/api/medicalCard";
  private static API_SCHEDULE = "/api/schedule";

  public static async getPatientList() {
    return await Api.get<Patient[]>(this.BASE_URL + this.API_PATIENT)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getLatestPatients() {
    return await Api.get<Patient[]>(this.BASE_URL + this.API_PATIENT + "/latest")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getPatientCount() {
    return await Api.get<number>(this.BASE_URL + this.API_PATIENT + "/count")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getPatientsStatisticsByGender() {
    return await Api.get<PatientStatisticsByGenderDTO>(this.BASE_URL + this.API_PATIENT + "/statistics/by-gender")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getPatientsStatisticsByAge() {
    return await Api.get<PatientStatisticsByAgeDTO>(this.BASE_URL + this.API_PATIENT + "/statistics/by-age")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async searchPatientList(firstName: string, patronymic: string, lastName: string) {
    return await Api.get<Patient[]>(this.BASE_URL + this.API_PATIENT, {
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

  public static async getDoctorList(page: number) {
    return await Api.get<Doctor[]>(this.BASE_URL + this.API_DOCTOR + "/" + page)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getDoctorCount() {
    return await Api.get<number>(this.BASE_URL + this.API_DOCTOR + "/count")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getMedicalCard(id: number) {
    return await Api.get<MedicalCard>(this.BASE_URL + this.API_MEDICAL_CARD + `/patient/${id}`)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async saveMedicalRecord(id: number, body: MedicalRecord) {
    return await Api.post<MedicalCard>(this.BASE_URL + this.API_MEDICAL_CARD + `/patient/${id}`, body)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getPatient(id: number) {
    return await Api.get<Patient>(this.BASE_URL + this.API_PATIENT + `/${id}`)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async scheduleByDay(date: string) {
    return await Api.get<Schedule>(this.BASE_URL + this.API_SCHEDULE + "/day", {
      params: { date: date },
    })
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getDoctor() {
    return await Api.get<Doctor>(this.BASE_URL + this.API_DOCTOR + "/home")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getScheduleByIdAndDoctor(id: number) {
    return await Api.get<Schedule>(this.BASE_URL + this.API_SCHEDULE + `/${id}`)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async addDoctorSchedule(body: ScheduleDTO) {
    return await Api.post<AppointmentDTO>(this.BASE_URL + this.API_SCHEDULE, body)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async addDoctorAppointment(id: number, body: AppointmentDTO) {
    return await Api.post<AppointmentDTO>(this.BASE_URL + this.API_SCHEDULE + `/${id}/appointment`, body)
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getLatestDoctorAppointments() {
    return await Api.get<Appointment[]>(this.BASE_URL + this.API_SCHEDULE + "/appointment/latest")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }

  public static async getAppointmentCountByDay() {
    return await Api.get<AppointmentsCountByDayDTO[]>(this.BASE_URL + this.API_SCHEDULE + "/appointment/count-by-day")
      .then((res) => res.data)
      .catch((error) => {
        throw error;
      });
  }
}

export default HttpService;
