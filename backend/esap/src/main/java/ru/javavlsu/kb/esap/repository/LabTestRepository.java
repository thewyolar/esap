package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.javavlsu.kb.esap.model.LabTest;

@Repository
public interface LabTestRepository extends JpaRepository<LabTest, Long> {
}
