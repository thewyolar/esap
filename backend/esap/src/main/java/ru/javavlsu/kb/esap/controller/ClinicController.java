package ru.javavlsu.kb.esap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.service.ClinicService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ClinicController {
    @Autowired
    private ClinicService clinicService;

    @GetMapping("/clinics")
    public List<Clinic> getAllClinics() { return clinicService.getAll(); }
}
