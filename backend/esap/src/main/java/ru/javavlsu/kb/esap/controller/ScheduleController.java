package ru.javavlsu.kb.esap.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.ScheduleResponseDTO;
import ru.javavlsu.kb.esap.mapper.ScheduleMapper;
import ru.javavlsu.kb.esap.model.Appointment;
import ru.javavlsu.kb.esap.model.Schedule;
import ru.javavlsu.kb.esap.security.DoctorDetails;
import ru.javavlsu.kb.esap.service.AppointmentService;
import ru.javavlsu.kb.esap.service.ScheduleService;
import ru.javavlsu.kb.esap.util.NotCreateException;
import ru.javavlsu.kb.esap.util.ResponseMessageError;
import ru.javavlsu.kb.esap.util.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final AppointmentService appointmentService;
    private final ScheduleMapper scheduleMapper;


    public ScheduleController(ScheduleService scheduleService, AppointmentService appointmentService, ScheduleMapper scheduleMapper) {
        this.scheduleService = scheduleService;
        this.appointmentService = appointmentService;
        this.scheduleMapper = scheduleMapper;
    }

    @GetMapping("/doctor/{doctorId}")
    public List<ScheduleResponseDTO> getDoctorSchedule(@PathVariable("doctorId") Long doctorId) {
        return scheduleService.getAllByDoctorId(doctorId).stream().map(scheduleMapper::toScheduleResponseDTO).toList();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDTO getSchedule(@PathVariable("id") Long id) {
        return scheduleMapper.toScheduleResponseDTO(scheduleService.getByIdAndDoctor(id, getDoctorDetails().getDoctor()));
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createSchedule(@RequestBody @Valid Schedule schedule,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        scheduleService.create(schedule);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/appointment")
    public ResponseEntity<HttpStatus> addAppointment(@PathVariable("id") Long id, @RequestBody @Valid Appointment appointment,
                                                     BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        appointmentService.create(appointment, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/day")
    public ScheduleResponseDTO scheduleByDayForDoctor(@RequestParam LocalDate data) {
        return scheduleMapper.toScheduleResponseDTO(scheduleService.getByDateAndDoctor(data, getDoctorDetails().getDoctor()));
    }

    private DoctorDetails getDoctorDetails(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (DoctorDetails) authentication.getPrincipal();
    }

    @ExceptionHandler
    private ResponseEntity<NotCreateException> notCreateException(NotCreateException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<NotFoundException> notFoundException(NotFoundException e) {
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

}
