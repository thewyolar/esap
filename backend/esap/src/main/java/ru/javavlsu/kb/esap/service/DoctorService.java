package ru.javavlsu.kb.esap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.repository.DoctorRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAll() { return doctorRepository.findAll(); }
    
    @Transactional
    public void save(Doctor doctor){
        doctorRepository.save(doctor);
    }
}
