import { clinicDTO } from "./ClinicDTO";
import { doctorDTO } from "./DoctorDTO";

export type clinicRegistrationDTO = {
    clinic: clinicDTO;
    doctor: doctorDTO;
}