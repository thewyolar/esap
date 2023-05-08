package ru.javavlsu.kb.esap.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.dto.auth.AuthenticationDTO;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.repository.ClinicRepository;
import ru.javavlsu.kb.esap.repository.DoctorRepository;
import ru.javavlsu.kb.esap.repository.RoleRepository;
import ru.javavlsu.kb.esap.util.LoginPasswordGenerator;
import ru.javavlsu.kb.esap.exception.NotFoundException;

import java.util.Collections;
import java.util.HashSet;

@Service
@Transactional(readOnly = true)
public class RegistrationService {

    private final ClinicRepository clinicRepository;
    private final PasswordEncoder passwordEncoder;
    private final DoctorRepository doctorRepository;
    private final RoleRepository roleRepository;

    public RegistrationService(ClinicRepository clinicRepository, PasswordEncoder passwordEncoder, DoctorRepository doctorRepository, RoleRepository roleRepository) {
        this.clinicRepository = clinicRepository;
        this.passwordEncoder = passwordEncoder;
        this.doctorRepository = doctorRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public String[] registrationClinic(Clinic clinic, Doctor doctor) throws NotFoundException {
        String login = LoginPasswordGenerator.generateLogin();
        String password = LoginPasswordGenerator.generatePassword();
        doctor.setLogin(login);
        doctor.setPassword(passwordEncoder.encode(password));
        doctor.setClinic(clinic);
        doctor.setRole(new HashSet<>());
        doctor.getRole().add(roleRepository.findByName("ROLE_CHIEF_DOCTOR")
                .orElseThrow(() -> new NotFoundException("Role not found")));
        clinic.setDoctors(Collections.singletonList(doctor));
        clinicRepository.save(clinic);
        return new String[]{login, password};
    }

    @Transactional
    public void passwordReset(AuthenticationDTO authenticationDTO) {
        Doctor doctor = doctorRepository.findByLogin(authenticationDTO.getLogin())
                .orElseThrow(() -> new NotFoundException("Doctor not found"));

        doctor.setPassword(passwordEncoder.encode(authenticationDTO.getPassword()));
        doctorRepository.save(doctor);
    }
}
