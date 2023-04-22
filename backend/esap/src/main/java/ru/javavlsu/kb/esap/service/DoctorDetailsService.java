package ru.javavlsu.kb.esap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.repository.DoctorRepository;
import ru.javavlsu.kb.esap.security.DoctorDetails;
import ru.javavlsu.kb.esap.util.NotFoundException;

import java.util.Optional;

@Service
public class DoctorDetailsService implements UserDetailsService {

    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorDetailsService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws NotFoundException {
        Optional<Doctor> doctor = doctorRepository.findByLogin(username);
        if(doctor.isEmpty()){
            throw new NotFoundException("User not found");
        }
        return new DoctorDetails(doctor.get());
    }
}
