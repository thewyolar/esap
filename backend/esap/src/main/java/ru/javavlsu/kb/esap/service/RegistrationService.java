package ru.javavlsu.kb.esap.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.repository.ClinicRepository;

import java.util.Collections;

@Service
@Transactional(readOnly = true)
public class RegistrationService {

    private final ClinicRepository clinicRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(ClinicRepository clinicRepository, PasswordEncoder passwordEncoder) {
        this.clinicRepository = clinicRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public String[] registrationClinic(Clinic clinic, Doctor doctor) {
        String[] loginPassword = {"admin", "admin"};//TODO генератор паролей
        doctor.setLogin(loginPassword[0]);
        doctor.setPassword(passwordEncoder.encode(loginPassword[1]));
        doctor.setClinic(clinic);
        clinic.setDoctors(Collections.singletonList(doctor));
        clinicRepository.save(clinic);
        return new String[] {doctor.getLogin(), loginPassword[1]};
    }

}
