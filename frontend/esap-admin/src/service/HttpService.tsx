import axios from "axios";
import { ClinicRegistrationDTO } from "../model/dto/ClinicRegistrationDTO";
import {Patient} from "../model/Patient";

class HttpService {

    private static url = "http://localhost:8080";

    public static async registrationClinics(body: ClinicRegistrationDTO){
        return await axios.post(HttpService.url + "/api/clinic", body)
        .then((res) => res.data)
        .catch((error) => error)
    }

    public static async getPatientList() {
        return await axios.get<Patient[]>(HttpService.url + "/api/patient")
          .then((res) => res.data)
          .catch((error) => error)
    }
}

export default HttpService;