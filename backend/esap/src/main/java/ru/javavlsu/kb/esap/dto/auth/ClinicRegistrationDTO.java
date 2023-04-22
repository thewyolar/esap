package ru.javavlsu.kb.esap.dto.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClinicRegistrationDTO {
    
    private ClinicRegistration clinic;

    private DoctorRegistration doctor;
}
