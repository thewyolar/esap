import axios, { AxiosInstance } from "axios";
import { TokenStorageService } from "./TokenStorageService";

const tokenStorageService = new TokenStorageService();

const Api: AxiosInstance = axios.create({
  headers: {
    "Content-Type": "application/json",
    Authorization: `Bearer ${tokenStorageService.getToken()}`,
  },
});

Api.interceptors.response.use(
  (response) => response,
  (error) => {
    console.log(error);
    window.location.href = "/login";
    return Promise.reject(error);
  }
);

export default Api;
