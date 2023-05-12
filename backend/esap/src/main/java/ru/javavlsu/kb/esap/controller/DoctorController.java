package ru.javavlsu.kb.esap.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javavlsu.kb.esap.dto.DoctorDTO;
import ru.javavlsu.kb.esap.mapper.DoctorMapper;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.security.DoctorDetails;
import ru.javavlsu.kb.esap.service.DoctorService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    private final DoctorMapper doctorMapper;

    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
    }

    @GetMapping
    public List<DoctorDTO> getAllDoctors() {
        Doctor doctor = getDoctorDetails().getDoctor();
        return doctorMapper.toDoctorDTOList(doctorService.getByClinic(doctor.getClinic()));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getPatientsCount() {
        Doctor doctor = getDoctorDetails().getDoctor();
        return ResponseEntity.ok(doctorService.getDoctorCountByClinic(doctor.getClinic()));
    }

    @GetMapping("/home")
    public DoctorDTO getDoctor() {
        Doctor doctor = getDoctorDetails().getDoctor();
        doctor = doctorService.refreshDoctor(doctor);
        return doctorMapper.toDoctorDTO(doctor);
    }

    private DoctorDetails getDoctorDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (DoctorDetails) authentication.getPrincipal();
    }
}
