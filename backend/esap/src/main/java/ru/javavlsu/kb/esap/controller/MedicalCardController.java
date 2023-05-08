package ru.javavlsu.kb.esap.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.MedicalCardDTO.MedicalCardResponseDTO;
import ru.javavlsu.kb.esap.dto.MedicalCardDTO.MedicalRecordRequestDTO;
import ru.javavlsu.kb.esap.mapper.MedicalCardMapper;
import ru.javavlsu.kb.esap.security.DoctorDetails;
import ru.javavlsu.kb.esap.service.MedicalCardService;
import ru.javavlsu.kb.esap.service.PatientService;
import ru.javavlsu.kb.esap.exception.NotCreateException;
import ru.javavlsu.kb.esap.exception.ResponseMessageError;

@RestController
@CrossOrigin
@RequestMapping("/api/medicalCard")
public class MedicalCardController {

    private final MedicalCardService medicalCardService;

    private final MedicalCardMapper medicalCardMapper;

    private final PatientService patientService;


    public MedicalCardController(MedicalCardService medicalCardService, MedicalCardMapper medicalCardMapper, PatientService patientService) {
        this.medicalCardService = medicalCardService;
        this.medicalCardMapper = medicalCardMapper;
        this.patientService = patientService;
    }


    @GetMapping("/patient/{id}")
    public MedicalCardResponseDTO getMedicalCard(@PathVariable("id") Long id) {
        return medicalCardMapper.toMedicalCard(
                medicalCardService.getMedicalCardByPatient(patientService.getById(id)));
    }

    @PostMapping("/patient/{id}")
    public ResponseEntity<HttpStatus> saveMedicalRecord(@PathVariable("id") Long id,
                                                        @Valid @RequestBody MedicalRecordRequestDTO medicalRecordRequestDTO,
                                                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        System.out.println(medicalRecordRequestDTO);
        medicalCardService.createMedicalRecord(medicalCardMapper.toMedicalRecordRequestDTO(medicalRecordRequestDTO),
                medicalCardService.getMedicalCardByPatient(patientService.getById(id)), getDoctorDetails().getDoctor());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private DoctorDetails getDoctorDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (DoctorDetails) authentication.getPrincipal();
    }
}
