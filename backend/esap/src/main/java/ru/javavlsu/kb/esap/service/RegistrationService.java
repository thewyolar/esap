package ru.javavlsu.kb.esap.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.repository.ClinicRepository;
import ru.javavlsu.kb.esap.repository.DoctorRepository;

import java.util.Collections;
import java.util.Random;

@Service
@Transactional(readOnly = true)
public class RegistrationService {

    private final ClinicRepository clinicRepository;
    private final PasswordEncoder passwordEncoder;
    private final DoctorRepository doctorRepository;

    public RegistrationService(ClinicRepository clinicRepository, PasswordEncoder passwordEncoder, DoctorRepository doctorRepository) {
        this.clinicRepository = clinicRepository;
        this.passwordEncoder = passwordEncoder;
        this.doctorRepository = doctorRepository;
    }

    @Transactional
    public String[] registrationClinic(Clinic clinic, Doctor doctor) {
        Random random = new Random();
        String[] loginPassword = new String[2];
        loginPassword[1] = "123";
        do {
            loginPassword[0] = String.valueOf(random.nextInt(10)) + random.nextInt(10) + random.nextInt(10);//TODO генератор паролей
        } while (doctorRepository.findByLogin(loginPassword[0]).isPresent());
        doctor.setLogin(loginPassword[0]);
        doctor.setPassword(passwordEncoder.encode(loginPassword[1]));
        doctor.setClinic(clinic);
        clinic.setDoctors(Collections.singletonList(doctor));
        clinicRepository.save(clinic);
        return new String[] {doctor.getLogin(), loginPassword[1]};
    }

}
