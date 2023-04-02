import { DoctorDTO } from "./dto/DoctorDTO";
import {Appointment} from "./Appointment";

export interface Shedule {
  id: number,
  doctor: DoctorDTO,
  date: string,
  startDoctorAppointment: string,
  endDoctorAppointment: string,
  appointments: Appointment[]
}