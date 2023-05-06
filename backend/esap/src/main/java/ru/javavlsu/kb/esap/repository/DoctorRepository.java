package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.Role;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByLogin(String login);
    List<Doctor> findByClinic(Clinic clinic);
}
