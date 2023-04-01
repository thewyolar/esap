import { ClinicDTO } from "./ClinicDTO";
import { DoctorDTO } from "./DoctorDTO";

export interface ClinicRegistrationDTO {
    clinic: ClinicDTO;
    doctor: DoctorDTO;
}