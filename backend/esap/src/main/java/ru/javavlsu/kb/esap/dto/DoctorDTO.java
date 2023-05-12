package ru.javavlsu.kb.esap.dto;


import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.ScheduleResponseDTO;
import ru.javavlsu.kb.esap.model.Clinic;

import java.util.List;

@Getter
@Setter
public class DoctorDTO {

    private Long id;

    private String login;

    private String firstName;

    @Size(max = 100)
    private String patronymic;

    private String lastName;

    private String specialization;

    private int gender;

    private Clinic clinic;

    private List<ScheduleResponseDTO> schedules;
}
