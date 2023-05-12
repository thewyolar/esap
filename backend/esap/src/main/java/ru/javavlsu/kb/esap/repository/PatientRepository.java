package ru.javavlsu.kb.esap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Patient;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient> findByClinic(Clinic clinic);

    Long countPatientByClinic(Clinic clinic);

    Page<Patient> findAllByClinicOrderByIdDesc(Clinic clinic, Pageable pageable);

    List<Patient> findAllByFirstNameContainingIgnoreCaseAndPatronymicContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndClinic(
            String firstName, String patronymic, String lastName, Clinic clinic);
}
