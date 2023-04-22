package ru.javavlsu.kb.esap.controller;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ru.javavlsu.kb.esap.dto.ClinicDTO;
import ru.javavlsu.kb.esap.dto.DoctorDTO;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.service.ClinicService;
import ru.javavlsu.kb.esap.util.NotCreateException;

import java.util.List;

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
