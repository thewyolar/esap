package ru.javavlsu.kb.esap.service;

import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.Role;
import ru.javavlsu.kb.esap.repository.DoctorRepository;
import ru.javavlsu.kb.esap.exception.NotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class DoctorService {

    private final EntityManager em;

    private final DoctorRepository doctorRepository;

    public DoctorService(EntityManager em, DoctorRepository doctorRepository) {
        this.em = em;
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    public int getDoctorCountByClinic(Clinic clinic) {
        return doctorRepository.countDoctorByClinic(clinic);
    }

    public Page<Doctor> getByClinic(Clinic clinic, int page) {
        return doctorRepository.findByClinic(clinic, PageRequest.of(page, 10));
    }

    public Doctor getById(long id) {
        return doctorRepository.findById(id).orElseThrow(() -> new NotFoundException("Doctor not found"));
    }

    public Doctor refreshDoctor(Doctor doctor) {
        return em.merge(doctor);
    }

    public List<String> getRoles(String login){
        return doctorRepository.findByLogin(login)
                .orElseThrow(() -> new NotFoundException("Doctor not found"))
                .getRole().stream().map(Role::getName).toList();
    }

    public boolean doctorExists(String login){
        return doctorRepository.findByLogin(login).isPresent();
    }

    @Transactional
    public void save(Doctor doctor) {
        doctorRepository.save(doctor);
    }
}
