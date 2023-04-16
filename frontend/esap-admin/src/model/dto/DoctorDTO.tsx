import {Schedule} from "../Schedule";

export interface DoctorDTO {
    id: number;
    firstName: string;
    patronymic: string;
    lastName: string;
    specialization: string;
    schedules: Schedule[];
}