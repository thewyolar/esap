package ru.javavlsu.kb.esap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.model.Registry;
import ru.javavlsu.kb.esap.service.ClinicService;
import ru.javavlsu.kb.esap.service.DoctorService;
import ru.javavlsu.kb.esap.service.PatientService;
import ru.javavlsu.kb.esap.service.RegistryService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {
    @Autowired
    private ClinicService clinicService;

    @Autowired
    private RegistryService registryService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/clinic/getAll")
    public List<Clinic> getAllClinics() { return clinicService.getAll(); }

    @GetMapping("/registry/getAll")
    public List<Registry> getAllRegistries() { return registryService.getAll(); }

    @GetMapping("/patient/getAll")
    public List<Patient> getAllPatients() { return patientService.getAll(); }

    @GetMapping("/doctor/getAll")
    public List<Doctor> getAllDoctors() { return doctorService.getAll(); }
}
