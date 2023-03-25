package ru.javavlsu.kb.esap.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.javavlsu.kb.esap.dto.ClinicDTO;
import ru.javavlsu.kb.esap.dto.DoctorDTO;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.service.ClinicService;
import ru.javavlsu.kb.esap.dto.ClinicRegistrationDTO;

import java.util.List;

@RestController
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
    public ResponseEntity<HttpStatus> registrationClinics(@RequestBody @Valid ClinicRegistrationDTO clinicRegistrationDTO,
                                                            BindingResult bindingResult) {               
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String[] loginPassword = clinicService.save(convertClinicDTO(clinicRegistrationDTO.getClinicDTO()),
                convertDoctorDTO(clinicRegistrationDTO.getDoctorDTO()));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Clinic convertClinicDTO(ClinicDTO clinicDTO){
        return modelMapper.map(clinicDTO, Clinic.class);
    }

    private Doctor convertDoctorDTO(DoctorDTO doctorDTO){
        return modelMapper.map(doctorDTO, Doctor.class);
    }
}
