package ru.javavlsu.kb.esap.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.MedicalCard;
import ru.javavlsu.kb.esap.model.MedicalRecord;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.repository.MedicalCardRepository;
import ru.javavlsu.kb.esap.repository.MedicalRecordRepository;
import ru.javavlsu.kb.esap.util.NotFoundException;

@Service
@Transactional(readOnly = true)
public class MedicalCardService {


    private final MedicalCardRepository medicalCardRepository;
    private final MedicalRecordRepository medicalRecordRepository;

    public MedicalCardService(MedicalCardRepository medicalCardRepository, MedicalRecordRepository medicalRecordRepository) {
        this.medicalCardRepository = medicalCardRepository;
        this.medicalRecordRepository = medicalRecordRepository;
    }

    public MedicalCard getMedicalCardByPatient(Patient patient) throws NotFoundException {
        return medicalCardRepository.findByPatient(patient).orElseThrow(() -> new NotFoundException("Medical Record not found"));
    }

    @Transactional
    public void createMedicalRecord(MedicalRecord medicalRecord, MedicalCard medicalCard, Doctor doctor) {
        medicalRecord.setFioAndSpecializationDoctor(doctor.getSpecialization() + ": " + doctor.getFio());
        medicalRecord.setMedicalCard(medicalCard);
        medicalRecord.getAnalyzes().forEach(analysis -> analysis.setMedicalRecord(medicalRecord));
        medicalRecordRepository.save(medicalRecord);
    }


}
