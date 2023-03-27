package ru.javavlsu.kb.esap.dto;

import lombok.Getter;
import lombok.Setter;
import ru.javavlsu.kb.esap.model.Appointment;
import ru.javavlsu.kb.esap.model.Doctor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class ScheduleDTO {

    private Doctor doctor;

    private LocalDate date;

    private LocalTime startDoctorAppointment;

    private LocalTime endDoctorAppointment;

    private List<Appointment> appointments;

}
