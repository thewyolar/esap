package ru.javavlsu.kb.esap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Registry;
import ru.javavlsu.kb.esap.repository.ClinicRepository;
import ru.javavlsu.kb.esap.repository.RegistryRepository;

import java.util.List;

@Service
public class RegistryService {

    @Autowired
    private RegistryRepository registryRepository;

    public List<Registry> getAll() { return registryRepository.findAll(); }
}
