package ru.javavlsu.kb.esap.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import ru.javavlsu.kb.esap.dto.ClinicDTO;
import ru.javavlsu.kb.esap.dto.DoctorDTO;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.service.ClinicService;
import ru.javavlsu.kb.esap.dto.ClinicRegistrationDTO;
import ru.javavlsu.kb.esap.util.NotCreateException;
import ru.javavlsu.kb.esap.util.ResponseMessageError;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/clinic")
public class ClinicController {

    private final ClinicService clinicService;
    private final ModelMapper modelMapper;

    public ClinicController(ClinicService clinicService, ModelMapper modelMapper) {
        this.clinicService = clinicService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Clinic> getAllClinics() { 
        return clinicService.getAll();
    }
    
    @PostMapping
    public Map<String, String> registrationClinics(@RequestBody @Valid ClinicRegistrationDTO clinicRegistrationDTO,
                                                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        String[] loginPassword = clinicService.save(convertClinicDTO(clinicRegistrationDTO.getClinic()),
                convertDoctorDTO(clinicRegistrationDTO.getDoctor()));
        return Map.of("login", loginPassword[0], "password", loginPassword[1]);
    }

    private Clinic convertClinicDTO(ClinicDTO clinicDTO){
        return modelMapper.map(clinicDTO, Clinic.class);
    }

    private Doctor convertDoctorDTO(DoctorDTO doctorDTO){
        return modelMapper.map(doctorDTO, Doctor.class);
    }

    @ExceptionHandler
    private ResponseEntity<NotCreateException> notCreateException(NotCreateException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

}
