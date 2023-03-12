package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
