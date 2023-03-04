package ru.javavlsu.kb.esap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.repository.ClinicRepository;

import java.util.List;

@Service
public class ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    public List<Clinic> getAll() { return clinicRepository.findAll(); }
}
