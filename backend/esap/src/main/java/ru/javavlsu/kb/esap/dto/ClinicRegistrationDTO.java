package ru.javavlsu.kb.esap.dto;

import lombok.Getter;
import lombok.Setter;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;

@Getter
@Setter
public class ClinicRegistrationDTO {
    
    private ClinicDTO clinicDTO;

    private DoctorDTO doctorDTO;

}
