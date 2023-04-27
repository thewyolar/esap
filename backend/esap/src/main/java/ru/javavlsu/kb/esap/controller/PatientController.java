package ru.javavlsu.kb.esap.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.PatientRequestDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.PatientResponseDTO;
import ru.javavlsu.kb.esap.mapper.PatientMapper;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.security.DoctorDetails;
import ru.javavlsu.kb.esap.service.PatientService;
import ru.javavlsu.kb.esap.util.NotCreateException;
import ru.javavlsu.kb.esap.util.ResponseMessageError;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;
    private final PatientMapper patientMapper;

    public PatientController(PatientService patientService, PatientMapper patientMapper) {
        this.patientService = patientService;
        this.patientMapper = patientMapper;
    }

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        Doctor doctor = getDoctorDetails().getDoctor();
        List<PatientResponseDTO> patients = patientService.getByClinic(doctor.getClinic());
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    public PatientResponseDTO getPatient(@PathVariable("id") Long patientId) {
        Patient patient = patientService.getById(patientId);
        return patientMapper.toPatientResponseDTO(patient);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO,
                                                    BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        patientService.create(patientRequestDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private DoctorDetails getDoctorDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (DoctorDetails) authentication.getPrincipal();
    }

    @ExceptionHandler
    private ResponseEntity<NotCreateException> notCreateException(NotCreateException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

}
