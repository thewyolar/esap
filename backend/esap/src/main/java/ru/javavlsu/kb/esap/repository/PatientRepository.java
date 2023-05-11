package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByClinic(Clinic clinic);

    List<Patient> findAllByFirstNameContainingIgnoreCaseAndPatronymicContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndClinic(
            String firstName, String patronymic, String lastName, Clinic clinic);
}
