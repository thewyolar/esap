package ru.javavlsu.kb.esap.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.dto.PatientRequestDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.PatientResponseDTO;
import ru.javavlsu.kb.esap.mapper.PatientMapper;
import ru.javavlsu.kb.esap.model.MedicalCard;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.repository.MedicalCardRepository;
import ru.javavlsu.kb.esap.repository.PatientRepository;
import ru.javavlsu.kb.esap.util.NotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PatientService {

    private final PatientRepository patientRepository;

    private final MedicalCardRepository medicalCardRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientRepository patientRepository, MedicalCardRepository medicalCardRepository, PatientMapper patientMapper) {
        this.medicalCardRepository = medicalCardRepository;
        this.patientMapper = patientMapper;
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAll() {
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.patientResponseDTOList(patients);
    }

    public Patient getById(long id) {
        return patientRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient not found"));
    }

    @Transactional
    public Patient create(PatientRequestDTO patientRequestDTO) {
        Patient patient = patientMapper.toPatient(patientRequestDTO);
        patient.setMedicalCard(new MedicalCard(patient));
        return patientRepository.save(patient);
    }

}
