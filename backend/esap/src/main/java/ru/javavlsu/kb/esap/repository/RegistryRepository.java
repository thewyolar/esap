package ru.javavlsu.kb.esap.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javavlsu.kb.esap.model.Registry;

public interface RegistryRepository extends JpaRepository<Registry, Long> {
}
