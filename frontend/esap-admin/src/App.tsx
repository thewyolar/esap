import { Routes, Route } from 'react-router-dom';
import Home from './pages/home/Home';
import NewProduct from './pages/newProduct/NewProduct';
import Test from './pages/newPatient/NewPatient';
import Product from './pages/product/Product';
import ProductList from './pages/productList/ProductList';
import User from './pages/user/User';
import PatientList from './pages/patientList/PatientList';
import HospitalRegistrationForm from "./components/auth/HospitalregistrationForm";
import 'bootstrap/dist/css/bootstrap.min.css';
import MainLayout from "./layout/MainLayout";
import RegistrationForm from "./components/auth/RegistrationForm";
import LoginForm from "./components/auth/LoginForm";
import React from "react";

const App: React.FC = () => {
  return (
    <Routes>
      <Route path="/" element={<MainLayout />}>
        <Route path="/" element={<Home />} />
        <Route path="/users" element={<PatientList />} />
        <Route path="/user/:userId" element={<User />} />
        <Route path="/newUser" element={<Test />} />
        <Route path="/products" element={<ProductList />} />
        <Route path="/product/:productId" element={<Product />} />
        <Route path="/newProduct" element={<NewProduct />} />
      </Route>
      <Route path="/login" element={<LoginForm />} />
      <Route path="/registration" element={<RegistrationForm />}></Route>
      <Route path="/hospitalregistration" element={<HospitalRegistrationForm />} />
    </Routes>
  );
};

export default App;
