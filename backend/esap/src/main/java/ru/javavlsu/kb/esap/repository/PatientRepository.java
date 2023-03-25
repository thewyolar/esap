package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.Patient;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
