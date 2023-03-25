package ru.javavlsu.kb.esap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.service.ClinicService;
import ru.javavlsu.kb.esap.dto.ClinicRegistrationDTO;

import java.util.List;

@RestController
@RequestMapping("/api/clinic")
public class ClinicController {

    private ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    public List<Clinic> getAllClinics() { 
        return clinicService.getAll();
    }
    
    @PostMapping
    public ResponseEntity<HttpStatus> registrationClinics(@RequestBody @Valid ClinicRegistrationDTO clinicRegistration,
                                                            BindingResult bindingResult) {               
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        String[] lodinPassword = clinicService.save(clinicRegistration.getClinic(), clinicRegistration.getDoctor());
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
