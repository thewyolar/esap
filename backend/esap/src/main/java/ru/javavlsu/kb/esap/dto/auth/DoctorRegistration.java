package ru.javavlsu.kb.esap.dto.auth;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.ScheduleResponseDTO;

import java.util.List;

@Getter
@Setter
public class DoctorRegistration {
    private Long id;

    private String password;

    private String login;

    private String firstName;

    @Size(max = 100)
    private String patronymic;

    private String lastName;

    private String specialization;

    private int gender;

    private List<ScheduleResponseDTO> schedules;

    private String role;
}
