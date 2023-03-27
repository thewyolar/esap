package ru.javavlsu.kb.esap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.repository.PatientRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAll() { return patientRepository.findAll(); }

    @Transactional
    public void create(Patient patient){
        patientRepository.save(patient);
    }

}
