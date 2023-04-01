import { Routes, Route } from 'react-router-dom';
import Home from './pages/home/Home';
import NewPatient from './pages/newPatient/NewPatient';
import User from './pages/user/User';
import PatientList from './pages/patientList/PatientList';
import 'bootstrap/dist/css/bootstrap.min.css';
import MainLayout from "./layout/MainLayout";
import LoginForm from "./components/auth/LoginForm";
import React from "react";
import RegistrationPage from "./pages/registration/Registration";
import Queue from "./components/queue/Queue";

const App: React.FC = () => {
  return (
    <Routes>
      <Route path="/" element={<MainLayout />}>
        <Route path="/" element={<Home />} />
        <Route path="/patients" element={<PatientList />} />
        <Route path="/patients/:patientId" element={<User />} />
        <Route path="/newPatient" element={<NewPatient />} />
        <Route path="/queue" element={<Queue />} />
      </Route>
      <Route path="/login" element={<LoginForm />} />
      <Route path="/register" element={<RegistrationPage />} />
    </Routes>
  );
};

export default App;
