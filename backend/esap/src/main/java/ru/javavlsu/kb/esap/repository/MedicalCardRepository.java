package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.MedicalCard;
import ru.javavlsu.kb.esap.model.Patient;

import java.util.Optional;

public interface MedicalCardRepository extends JpaRepository<MedicalCard, Long> {


    Optional<MedicalCard> findByPatientOrderByMedicalRecordDateDesc(Patient patient);

}
