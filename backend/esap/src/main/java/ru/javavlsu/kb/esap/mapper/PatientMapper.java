package ru.javavlsu.kb.esap.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.javavlsu.kb.esap.dto.PatientRequestDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.PatientResponseDTO;
import ru.javavlsu.kb.esap.model.Patient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PatientMapper {
    private final ModelMapper modelMapper;

    public PatientMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PatientRequestDTO toPatientRequestDTO(Patient patient) {
        return modelMapper.map(patient, PatientRequestDTO.class);
    }

    public PatientResponseDTO toPatientResponseDTO(Patient patient) {
        return modelMapper.map(patient, PatientResponseDTO.class);
    }

    public Patient toPatient(PatientRequestDTO patientRequestDTO) {
        return modelMapper.map(patientRequestDTO, Patient.class);
    }

    public Patient toPatient(PatientResponseDTO patientResponseDTO) {
        return modelMapper.map(patientResponseDTO, Patient.class);
    }

    public List<PatientResponseDTO> toPatientResponseDTOList(List<Patient> patients) {
        return patients.stream()
                .map(patient -> modelMapper.map(patient, PatientResponseDTO.class))
                .collect(Collectors.toList());
    }
}
