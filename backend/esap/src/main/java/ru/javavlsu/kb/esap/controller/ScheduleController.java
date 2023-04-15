package ru.javavlsu.kb.esap.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.ScheduleResponseDTO;
import ru.javavlsu.kb.esap.model.Appointment;
import ru.javavlsu.kb.esap.model.Schedule;
import ru.javavlsu.kb.esap.service.AppointmentService;
import ru.javavlsu.kb.esap.service.ScheduleService;
import ru.javavlsu.kb.esap.util.NotCreateException;
import ru.javavlsu.kb.esap.util.ResponseMessageError;
import ru.javavlsu.kb.esap.util.NotFoundException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final AppointmentService appointmentService;
    private final ModelMapper modelMapper;


    public ScheduleController(ScheduleService scheduleService, AppointmentService appointmentService, ModelMapper modelMapper) {
        this.scheduleService = scheduleService;
        this.appointmentService = appointmentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<ScheduleResponseDTO> getAllSchedule() {
        return scheduleService.getAll().stream().map(this::convertSchedule).toList();
    }

    @GetMapping("/{id}")
    public ScheduleResponseDTO getSchedule(@PathVariable("id") Long id) {
        return convertSchedule(scheduleService.get(id));
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

    private ScheduleResponseDTO convertSchedule(Schedule schedule) {
        return modelMapper.map(schedule, ScheduleResponseDTO.class);
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
