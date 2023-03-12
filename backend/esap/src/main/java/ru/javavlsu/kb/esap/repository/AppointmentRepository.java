package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
