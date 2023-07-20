package ru.javavlsu.kb.esap.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.AppointmentsCountByDayDTO;
import ru.javavlsu.kb.esap.dto.AppointmentDTO;
import ru.javavlsu.kb.esap.dto.ScheduleDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.AppointmentResponseDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.ScheduleResponseDTO;
import ru.javavlsu.kb.esap.mapper.AppointmentMapper;
import ru.javavlsu.kb.esap.mapper.ScheduleMapper;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.service.AppointmentService;
import ru.javavlsu.kb.esap.service.ScheduleService;
import ru.javavlsu.kb.esap.exception.NotCreateException;
import ru.javavlsu.kb.esap.exception.ResponseMessageError;
import ru.javavlsu.kb.esap.util.UserUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/api/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;
    private final AppointmentService appointmentService;
    private final ScheduleMapper scheduleMapper;
    private final AppointmentMapper appointmentMapper;
    private final UserUtils userUtils;

    public ScheduleController(ScheduleService scheduleService, AppointmentService appointmentService, ScheduleMapper scheduleMapper, AppointmentMapper appointmentMapper, UserUtils userUtils) {
        this.scheduleService = scheduleService;
        this.appointmentService = appointmentService;
        this.scheduleMapper = scheduleMapper;
        this.appointmentMapper = appointmentMapper;
        this.userUtils = userUtils;
    }

    @GetMapping("/doctor/{doctorId}")
    public List<ScheduleResponseDTO> getDoctorSchedule(@PathVariable("doctorId") Long doctorId) {
        return scheduleService.getAllByDoctorId(doctorId).stream().map(scheduleMapper::toScheduleResponseDTO).toList();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDTO getSchedule(@PathVariable("id") Long id) {
        return scheduleMapper.toScheduleResponseDTO(scheduleService.getByIdAndDoctor(id, userUtils.getDoctor()));
    }

    @PostMapping
    public ResponseEntity<Map<String, Long>> createSchedule(@RequestBody @Valid ScheduleDTO scheduleDTO,
                                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        return ResponseEntity.ok(Map.of("scheduleId", scheduleService.create(scheduleDTO).getId()));
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
    public List<ScheduleResponseDTO> getAppointmentsByDay(@RequestParam(required = false) LocalDate date) {
        Doctor doctor = userUtils.getDoctor();
        return scheduleService.getSchedulesByDay(date, doctor.getClinic());
    }

    @GetMapping("/appointment/latest")
    public List<AppointmentResponseDTO> getLatestAppointments(@RequestParam(name = "count", defaultValue = "5") Integer count) {
        Doctor doctor = userUtils.getDoctor();
        return appointmentService.getLatestAppointments(count, doctor);
    }

    @GetMapping("/appointment/count-by-day")
    public List<AppointmentsCountByDayDTO> getAppointmentsCountByDay() {
        Doctor doctor = userUtils.getDoctor();
        return appointmentService.getAppointmentsCountByDay(doctor);
    }
}
