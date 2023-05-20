package ru.javavlsu.kb.esap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByLogin(String login);
    Page<Doctor> findByClinic(Clinic clinic, Pageable page);
    int countDoctorByClinic(Clinic clinic);
}
