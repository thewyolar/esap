import axios from "axios";
import { ClinicRegistrationDTO } from "../model/dto/ClinicRegistrationDTO";

class HttpService {

    private static url = "http://localhost:8080";

    public static async registrationClinics(body: ClinicRegistrationDTO){
        return await axios.post(HttpService.url + "/api/clinic", body)
        .then((res) => res.data)
        .catch((error) => error)
    }
}

export default HttpService;