import {Patient} from "./Patient";

export interface Appointment {
  patient: Patient,
  date: string,
  startAppointments: string,
  endAppointments: string
}