package ru.javavlsu.kb.esap.dto;

import lombok.Getter;
import lombok.Setter;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;

@Getter
@Setter
public class ClinicRegistrationDTO {
    
    private Clinic clinic;

    private Doctor doctor;

}
