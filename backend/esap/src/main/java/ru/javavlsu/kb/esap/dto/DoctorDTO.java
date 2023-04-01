package ru.javavlsu.kb.esap.dto;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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
