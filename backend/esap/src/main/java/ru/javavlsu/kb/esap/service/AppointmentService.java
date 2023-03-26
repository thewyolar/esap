package ru.javavlsu.kb.esap.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.model.Appointment;
import ru.javavlsu.kb.esap.model.Schedule;
import ru.javavlsu.kb.esap.repository.AppointmentRepository;
import ru.javavlsu.kb.esap.repository.ScheduleRepository;
import ru.javavlsu.kb.esap.util.NotCreateException;
import ru.javavlsu.kb.esap.util.ScheduleNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, ScheduleRepository scheduleRepository) {
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public void create(Appointment appointment, long scheduleId) throws NotCreateException{
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(() -> new ScheduleNotFoundException("Schedule not found"));
        List<Appointment> appointments = appointmentRepository.findBySchedule(schedule);
        if(schedule.getMaxPatientPerDay() == appointments.size()){
            throw new NotCreateException("No free time found in the schedule");
        }
        appointments = appointmentRepository.findByStartAppointmentsAndDateAndSchedule(appointment.getStartAppointments(), appointment.getDate(), schedule);
        if(!appointments.isEmpty()){
            throw new NotCreateException("Time is already taken");
        }
        appointment.setSchedule(schedule);
        appointmentRepository.save(appointment);
    }
}
