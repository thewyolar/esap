package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.LabTest; 

public interface LabTestRepository extends JpaRepository<LabTest, Long> {
}
