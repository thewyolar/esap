package ru.javavlsu.kb.esap.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.auth.AuthenticationDTO;
import ru.javavlsu.kb.esap.dto.auth.ClinicRegistrationDTO;
import ru.javavlsu.kb.esap.mapper.ClinicMapper;
import ru.javavlsu.kb.esap.mapper.DoctorMapper;
import ru.javavlsu.kb.esap.security.JWTUtil;
import ru.javavlsu.kb.esap.service.RegistrationService;
import ru.javavlsu.kb.esap.util.NotCreateException;
import ru.javavlsu.kb.esap.util.NotFoundException;
import ru.javavlsu.kb.esap.util.ResponseMessageError;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final RegistrationService registrationService;
    private final ClinicMapper clinicMapper;
    private final DoctorMapper doctorMapper;

    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, RegistrationService registrationService, ClinicMapper clinicMapper, DoctorMapper doctorMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.registrationService = registrationService;
        this.clinicMapper = clinicMapper;
        this.doctorMapper = doctorMapper;
    }

    @PostMapping("/registration/clinic")
    public Map<String, String> registrationClinic(@RequestBody @Valid ClinicRegistrationDTO clinicRegistrationDTO,
                                                   BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        String[] loginPassword = registrationService.registrationClinic(clinicMapper.toClinic(clinicRegistrationDTO.getClinic()),
                doctorMapper.toDoctor(clinicRegistrationDTO.getDoctor()));
        return Map.of("login", loginPassword[0], "password", loginPassword[1]);
    }

    @PostMapping("/login")
    public Map<String, String> performLogin(@RequestBody AuthenticationDTO authenticationDTO){
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getLogin(), authenticationDTO.getPassword());
        System.out.println(authenticationToken);
        authenticationManager.authenticate(authenticationToken);
        String token = jwtUtil.generateToken(authenticationDTO.getLogin());
        return Map.of("jwt", token);
    }

    @ExceptionHandler
    private ResponseEntity<NotCreateException> notCreateException(NotCreateException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<NotFoundException> notFoundException(NotFoundException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

}
