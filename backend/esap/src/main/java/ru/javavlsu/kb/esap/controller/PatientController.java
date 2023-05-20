package ru.javavlsu.kb.esap.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.PatientRequestDTO;
import ru.javavlsu.kb.esap.dto.PatientStatisticsByAgeDTO;
import ru.javavlsu.kb.esap.dto.PatientStatisticsByGenderDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.PatientResponseDTO;
import ru.javavlsu.kb.esap.mapper.PatientMapper;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.service.PatientService;
import ru.javavlsu.kb.esap.exception.NotCreateException;
import ru.javavlsu.kb.esap.exception.ResponseMessageError;
import ru.javavlsu.kb.esap.util.DoctorUtils;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/patient")
public class PatientController {
    private final PatientService patientService;
    private final PatientMapper patientMapper;
    private final DoctorUtils doctorUtils;

    public PatientController(PatientService patientService, PatientMapper patientMapper, DoctorUtils doctorUtils) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
        this.doctorUtils = doctorUtils;
    }

    @GetMapping("")
    public ResponseEntity<Page<PatientResponseDTO>> getAllPatients(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String patronymic,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false, defaultValue = "0") int page
    ) {
        Doctor doctor = doctorUtils.getDoctorDetails().getDoctor();
        Page<PatientResponseDTO> patients = patientService.getByClinic(firstName, patronymic, lastName, doctor.getClinic(), page);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getPatientsCount() {
        Doctor doctor = doctorUtils.getDoctorDetails().getDoctor();
        return ResponseEntity.ok(patientService.getPatientCountByClinic(doctor.getClinic()));
    }

    @GetMapping("/latest")
    public ResponseEntity<List<PatientResponseDTO>> getLatestPatients(@RequestParam(defaultValue = "5") int count) {
        Doctor doctor = doctorUtils.getDoctorDetails().getDoctor();
        return ResponseEntity.ok(patientService.getLatestPatients(count, doctor.getClinic()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatient(@PathVariable("id") Long patientId) {
        Patient patient = patientService.getById(patientId);
        return ResponseEntity.ok(patientMapper.toPatientResponseDTO(patient));
    }

    @PostMapping
    @PreAuthorize("hasRole('REGISTRANT')")
    public ResponseEntity<HttpStatus> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        patientService.create(patientRequestDTO, doctorUtils.getDoctorDetails().getDoctor().getClinic());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("{id}/update")
    @PreAuthorize("hasRole('REGISTRANT')")
    public ResponseEntity<HttpStatus> updatePatient(@PathVariable("id") Long patientId, @Valid @RequestBody PatientRequestDTO patientRequestDTO,
                                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        patientService.update(patientId, patientRequestDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/statistics/by-gender")
    public ResponseEntity<PatientStatisticsByGenderDTO> getPatientStatisticsByGender() {
        Doctor doctor = doctorUtils.getDoctorDetails().getDoctor();
        return ResponseEntity.ok(patientService.getPatientsStatisticsByGender(doctor.getClinic()));
    }

    @GetMapping("/statistics/by-age")
    public ResponseEntity<PatientStatisticsByAgeDTO> getPatientStatisticsByAge() {
        Doctor doctor = doctorUtils.getDoctorDetails().getDoctor();
        return ResponseEntity.ok(patientService.getPatientsStatisticsByAge(doctor.getClinic()));
    }
}
