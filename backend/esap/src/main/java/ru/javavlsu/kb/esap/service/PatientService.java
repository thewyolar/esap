package ru.javavlsu.kb.esap.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.dto.PatientRequestDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.PatientResponseDTO;
import ru.javavlsu.kb.esap.mapper.PatientMapper;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.MedicalCard;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.repository.MedicalCardRepository;
import ru.javavlsu.kb.esap.repository.PatientRepository;
import ru.javavlsu.kb.esap.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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

    public Long getPatientCountByClinic(Clinic clinic) {
        return patientRepository.countPatientByClinic(clinic);
    }

    public List<PatientResponseDTO> getByClinic(String firstName, String patronymic, String lastName, Clinic clinic) {
        List<Patient> patients = patientRepository.findAllByFirstNameContainingIgnoreCaseAndPatronymicContainingIgnoreCaseAndLastNameContainingIgnoreCaseAndClinic(
                firstName != null ? firstName : "",
                patronymic != null ? patronymic : "",
                lastName != null ? lastName : "",
                clinic
        );
//        List<Patient> patients = patientRepository.findByClinic(clinic);
        return patientMapper.patientResponseDTOList(patients);
    }

    public Patient getById(long id) {
        return patientRepository.findById(id).orElseThrow(() -> new NotFoundException("Patient not found"));
    }

    @Transactional
    public Patient create(PatientRequestDTO patientRequestDTO, Clinic clinic) {
        Patient patient = patientMapper.toPatient(patientRequestDTO);
        patient.setClinic(clinic);
        patient.setMedicalCard(new MedicalCard(patient));
        return patientRepository.save(patient);
    }

}
