package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.Schedule;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByDoctorId(Long id);

    List<Schedule> findAllByDoctor(Doctor doctor);

    Optional<Schedule> findByDateAndDoctor(LocalDate date, Doctor doctor);

    Optional<Schedule> findByIdAndDoctor(Long id, Doctor doctor);

    void deleteScheduleByDateBefore(LocalDate date);
}
