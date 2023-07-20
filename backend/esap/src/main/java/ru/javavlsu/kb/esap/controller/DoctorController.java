package ru.javavlsu.kb.esap.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.DoctorDTO;
import ru.javavlsu.kb.esap.mapper.DoctorMapper;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.service.DoctorService;
import ru.javavlsu.kb.esap.util.DoctorUtils;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;
    private final DoctorUtils doctorUtils;

    public DoctorController(DoctorService doctorService, DoctorMapper doctorMapper, DoctorUtils doctorUtils) {
        this.doctorService = doctorService;
        this.doctorMapper = doctorMapper;
        this.doctorUtils = doctorUtils;
    }

    @GetMapping("")
    public ResponseEntity<Page<DoctorDTO>> getAllDoctors(@RequestParam(required = false, defaultValue = "0") int page) {
        Doctor doctor = doctorUtils.getDoctorDetails().getDoctor();
        return ResponseEntity.ok(doctorService.getByClinic(doctor.getClinic(), page));
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getDoctorCount() {
        Doctor doctor = doctorUtils.getDoctorDetails().getDoctor();
        return ResponseEntity.ok(doctorService.getDoctorCountByClinic(doctor.getClinic()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable("id") Long doctorId) {
        Doctor doctor = doctorService.getById(doctorId);
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(doctor));
    }

    @PostMapping("{id}/update")
    public ResponseEntity<HttpStatus> updateDoctor(@PathVariable("id") Long doctorId, @RequestBody DoctorDTO doctorDTO) {
        doctorService.update(doctorId, doctorDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/home")
    public ResponseEntity<DoctorDTO> getDoctor() {
        Doctor doctor = doctorUtils.getDoctorDetails().getDoctor();
        doctor = doctorService.refreshDoctor(doctor);
        return ResponseEntity.ok(doctorMapper.toDoctorDTO(doctor));
    }
}
