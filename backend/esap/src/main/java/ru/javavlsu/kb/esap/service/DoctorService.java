package ru.javavlsu.kb.esap.service;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.repository.DoctorRepository;
import ru.javavlsu.kb.esap.util.NotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DoctorService {

    @Autowired
    private EntityManager em;

    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAll() { return doctorRepository.findAll(); }

    public List<Doctor> getByClinic(Clinic clinic){
        return doctorRepository.findByClinic(clinic);
    }

    public Doctor getById(long id){
        return doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Doctor not found"));
    }

    public Doctor refreshDoctor(Doctor doctor) {
        return em.merge(doctor);
    }
    
    @Transactional
    public void save(Doctor doctor){
        doctorRepository.save(doctor);
    }
}
