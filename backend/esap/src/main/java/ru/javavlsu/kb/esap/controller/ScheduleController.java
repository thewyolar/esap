package ru.javavlsu.kb.esap.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javavlsu.kb.esap.model.Appointment;
import ru.javavlsu.kb.esap.model.Schedule;
import ru.javavlsu.kb.esap.service.AppointmentService;
import ru.javavlsu.kb.esap.service.ScheduleService;
import ru.javavlsu.kb.esap.util.NotCreateException;
import ru.javavlsu.kb.esap.util.ResponseMessageError;
import ru.javavlsu.kb.esap.util.ScheduleNotFoundException;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final AppointmentService appointmentService;


    public ScheduleController(ScheduleService scheduleService, AppointmentService appointmentService) {
        this.scheduleService = scheduleService;
        this.appointmentService = appointmentService;
    }

    @GetMapping
    public List<Schedule> getAllSchedule(){
        return scheduleService.getAll();
    }

    @GetMapping("/{id}")
    public Schedule getSchedule(@PathVariable("id") Long id){
        return scheduleService.get(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createSchedule(@RequestBody @Valid Schedule schedule,
                                                     BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        scheduleService.create(schedule);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{id}/appointment")
    public ResponseEntity<HttpStatus> addAppointment(@PathVariable("id") Long id, @RequestBody @Valid Appointment appointment,
                                                     BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new NotCreateException(ResponseMessageError.createErrorMsg(bindingResult.getFieldErrors()));
        }
        appointmentService.create(appointment, id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<NotCreateException> notCreateException(NotCreateException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ScheduleNotFoundException> scheduleNotFoundException(ScheduleNotFoundException e){
        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
    }

}
