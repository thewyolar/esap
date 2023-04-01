package ru.javavlsu.kb.esap.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.PatientRequestDTO;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.service.PatientService;
import ru.javavlsu.kb.esap.util.NotCreateException;
import ru.javavlsu.kb.esap.util.ResponseMessageError;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/patient")
public class PatientController {

    private final PatientService patientService;
    private final ModelMapper modelMapper;

    public PatientController(PatientService patientService, ModelMapper modelMapper) {
        this.patientService = patientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Patient> getAllPatients() { return patientService.getAll(); }

    @PostMapping
    public ResponseEntity<HttpStatus> createPatient(@Valid @RequestBody PatientRequestDTO patientRequestDTO,
                                                    BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        patientService.create(convertPatientDTO(patientRequestDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Patient convertPatientDTO(PatientRequestDTO patientRequestDTO){
        return modelMapper.map(patientRequestDTO, Patient.class);
    }

    @ExceptionHandler
    private ResponseEntity<NotCreateException> notCreateException(NotCreateException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

}
