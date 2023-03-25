package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.Clinic;

public interface ClinicRepository extends JpaRepository<Clinic, Long> {
}
