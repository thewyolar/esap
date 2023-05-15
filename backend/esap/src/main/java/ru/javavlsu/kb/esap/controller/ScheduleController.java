package ru.javavlsu.kb.esap.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.AppointmentDTO;
import ru.javavlsu.kb.esap.dto.ScheduleDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.AppointmentResponseDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.ScheduleResponseDTO;
import ru.javavlsu.kb.esap.mapper.AppointmentMapper;
import ru.javavlsu.kb.esap.mapper.ScheduleMapper;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.security.DoctorDetails;
import ru.javavlsu.kb.esap.service.AppointmentService;
import ru.javavlsu.kb.esap.service.ScheduleService;
import ru.javavlsu.kb.esap.exception.NotCreateException;
import ru.javavlsu.kb.esap.exception.ResponseMessageError;
import ru.javavlsu.kb.esap.util.DoctorUtils;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final AppointmentService appointmentService;
    private final ScheduleMapper scheduleMapper;
    private final AppointmentMapper appointmentMapper;
    private final DoctorUtils doctorUtils;

    public ScheduleController(ScheduleService scheduleService, AppointmentService appointmentService, ScheduleMapper scheduleMapper, AppointmentMapper appointmentMapper, DoctorUtils doctorUtils) {
        this.scheduleService = scheduleService;
        this.appointmentService = appointmentService;
        this.scheduleMapper = scheduleMapper;
        this.appointmentMapper = appointmentMapper;
        this.doctorUtils = doctorUtils;
    }

    @GetMapping("/doctor/{doctorId}")
    public List<ScheduleResponseDTO> getDoctorSchedule(@PathVariable("doctorId") Long doctorId) {
        return scheduleService.getAllByDoctorId(doctorId).stream().map(scheduleMapper::toScheduleResponseDTO).toList();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDTO getSchedule(@PathVariable("id") Long id) {
        return scheduleMapper.toScheduleResponseDTO(scheduleService.getByIdAndDoctor(id, doctorUtils.getDoctorDetails().getDoctor()));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createSchedule(@RequestBody @Valid ScheduleDTO scheduleDTO,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        scheduleService.create(scheduleDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/appointment")
    public ResponseEntity<HttpStatus> addAppointment(@PathVariable("id") Long id, @RequestBody @Valid AppointmentDTO appointmentDTO,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        appointmentService.create(appointmentDTO, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/day")
    public ScheduleResponseDTO scheduleByDayForDoctor(@RequestParam LocalDate data) {
        return scheduleMapper.toScheduleResponseDTO(scheduleService.getByDateAndDoctor(data, doctorUtils.getDoctorDetails().getDoctor()));
    }

    @GetMapping("/appointment/latest")
    public List<AppointmentResponseDTO> getLatestAppointments(@RequestParam(name = "count", defaultValue = "5") Integer count) {
        Doctor doctor = doctorUtils.getDoctorDetails().getDoctor();
        return appointmentService.getLatestAppointments(count, doctor);
    }
}
