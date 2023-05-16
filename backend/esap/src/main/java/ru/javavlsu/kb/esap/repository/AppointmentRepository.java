package ru.javavlsu.kb.esap.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.javavlsu.kb.esap.dto.AppointmentsCountByDayDTO;
import ru.javavlsu.kb.esap.model.Appointment;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    List<Appointment> findBySchedule(Schedule schedule);

    @Query("SELECT NEW ru.javavlsu.kb.esap.dto.AppointmentsCountByDayDTO(a.date, COUNT(a)) " +
            "FROM Appointment a WHERE a.schedule.doctor.clinic = :clinic " +
            "GROUP BY a.date ORDER BY a.date ASC")
    List<AppointmentsCountByDayDTO> countAppointmentsByDay(@Param("clinic") Clinic clinic);
    List<Appointment> findByStartAppointmentsAndDateAndSchedule(LocalTime startAppointment, LocalDate date, Schedule schedule);
    Page<Appointment> findByScheduleDoctorOrderByStartAppointmentsDesc(Doctor doctor, Pageable pageable);
}
