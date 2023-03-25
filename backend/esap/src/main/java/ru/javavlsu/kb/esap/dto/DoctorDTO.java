package ru.javavlsu.kb.esap.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Registry;
import ru.javavlsu.kb.esap.model.Schedule;

import java.util.List;

@Getter
@Setter
public class DoctorDTO {

    private String password;

    private String login;

    private String firstName;

    @Size(max = 100)
    private String patronymic;

    private String lastName;

    private String specialization;


}
