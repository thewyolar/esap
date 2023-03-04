package ru.javavlsu.kb.esap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.service.ClinicService;
import ru.javavlsu.kb.esap.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/patients")
    public List<Patient> getAllPatients() { return patientService.getAll(); }
}
