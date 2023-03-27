import axios from "axios";
import { clinicRegistrationDTO } from "../model/dto/ClinicRegistrationDTO";


export default class HttpService{

    private static url = "http://localhost:8080";

    public static async registrationClinics(body: clinicRegistrationDTO){
        return await axios.post(HttpService.url + "/api/clinic/", body)
        .then((res) => res.data)
        .catch((error) => error)
    }

}