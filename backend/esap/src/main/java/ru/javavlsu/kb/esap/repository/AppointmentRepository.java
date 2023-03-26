package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.Appointment;
import ru.javavlsu.kb.esap.model.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findBySchedule(Schedule schedule);
    List<Appointment> findByStartAppointmentsAndDateAndSchedule(LocalTime startAppointment, LocalDate date, Schedule schedule);

}
