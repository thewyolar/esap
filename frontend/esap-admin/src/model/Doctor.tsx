import {Schedule} from "./Schedule";

export interface Doctor {
    id: number;
    firstName: string;
    patronymic: string;
    lastName: string;
    specialization: string;
    schedules: Schedule[];
}