package ru.javavlsu.kb.esap.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "shedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Doctor doctor;

    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate date;

    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime startDoctorAppointment;

    @JsonSerialize(using = LocalTimeSerializer.class)
    private LocalTime endDoctorAppointment;

    private int maxPatientPerDay;


    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

}
