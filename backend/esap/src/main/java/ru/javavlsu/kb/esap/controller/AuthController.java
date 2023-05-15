package ru.javavlsu.kb.esap.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.auth.AuthenticationDTO;
import ru.javavlsu.kb.esap.dto.auth.ClinicRegistrationDTO;
import ru.javavlsu.kb.esap.dto.auth.DoctorRegistration;
import ru.javavlsu.kb.esap.exception.NotCreateException;
import ru.javavlsu.kb.esap.exception.ResponseMessageError;
import ru.javavlsu.kb.esap.mapper.ClinicMapper;
import ru.javavlsu.kb.esap.mapper.DoctorMapper;
import ru.javavlsu.kb.esap.security.JWTUtil;
import ru.javavlsu.kb.esap.service.DoctorService;
import ru.javavlsu.kb.esap.service.RegistrationService;
import ru.javavlsu.kb.esap.util.DoctorUtils;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RegistrationService registrationService;
    private final ClinicMapper clinicMapper;
    private final DoctorMapper doctorMapper;
    private final DoctorService doctorService;
    private final DoctorUtils doctorUtils;

    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RegistrationService registrationService, ClinicMapper clinicMapper, DoctorMapper doctorMapper, DoctorService doctorService, DoctorUtils doctorUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.registrationService = registrationService;
        this.clinicMapper = clinicMapper;
        this.doctorMapper = doctorMapper;
        this.doctorService = doctorService;
        this.doctorUtils = doctorUtils;
    }

    @PostMapping("/registration/clinic")
    public Map<String, String> registrationClinic(@RequestBody @Valid ClinicRegistrationDTO clinicRegistrationDTO,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        String[] loginPassword = registrationService.registrationClinic(clinicMapper.toClinic(clinicRegistrationDTO.getClinic()),
                doctorMapper.toDoctor(clinicRegistrationDTO.getDoctor()));
        return Map.of("login", loginPassword[0], "password", loginPassword[1]);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword());
        authenticationManager.authenticate(authenticationToken);
        String token = jwtUtil.generateToken(authenticationDTO.getLogin());
        return Map.of("jwt", token, "roles", String.join(";", doctorService.getRoles(authenticationDTO.getLogin())));
    }

    @PostMapping("/password/reset")
    public ResponseEntity<HttpStatus> passwordReset(@RequestBody AuthenticationDTO authenticationDTO) {
        registrationService.passwordReset(authenticationDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/registration/doctor")
    @PreAuthorize("hasRole('CHIEF_DOCTOR')")
    public Map<String, String> registrationDoctor(@RequestBody DoctorRegistration doctorRegistration) {
        String[] loginPassword = registrationService.registrationDoctor(doctorRegistration, doctorUtils.getDoctorDetails().getDoctor().getClinic());
        return Map.of("login", loginPassword[0], "password", loginPassword[1]);
    }
}
