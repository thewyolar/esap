package ru.javavlsu.kb.esap.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicRegistrationDTO {
    
    private ClinicDTO clinic;

    private DoctorDTO doctor;
}
