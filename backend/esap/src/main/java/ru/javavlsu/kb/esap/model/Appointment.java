package ru.javavlsu.kb.esap.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @Column(name = "start_time")
    private LocalDateTime startAppointments;

    @Column(name = "end_time")
    private LocalDateTime endAppointments;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

}