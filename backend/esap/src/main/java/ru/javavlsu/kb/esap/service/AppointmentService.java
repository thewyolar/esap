package ru.javavlsu.kb.esap.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.dto.AppointmentDTO;
import ru.javavlsu.kb.esap.mapper.AppointmentMapper;
import ru.javavlsu.kb.esap.model.Appointment;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.model.Schedule;
import ru.javavlsu.kb.esap.repository.AppointmentRepository;
import ru.javavlsu.kb.esap.repository.PatientRepository;
import ru.javavlsu.kb.esap.repository.ScheduleRepository;
import ru.javavlsu.kb.esap.util.NotCreateException;
import ru.javavlsu.kb.esap.util.NotFoundException;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleRepository scheduleRepository;
    private final PatientRepository patientRepository;
    private final AppointmentMapper appointmentMapper;

    public AppointmentService(AppointmentRepository appointmentRepository, ScheduleRepository scheduleRepository, PatientRepository patientRepository, AppointmentMapper appointmentMapper) {
        this.appointmentRepository = appointmentRepository;
        this.scheduleRepository = scheduleRepository;
        this.patientRepository = patientRepository;
        this.appointmentMapper = appointmentMapper;
    }

    @Transactional
    public void create(AppointmentDTO appointmentDTO, long scheduleId) throws NotCreateException{
        Appointment appointment = appointmentMapper.toAppointment(appointmentDTO);
        appointment.setEndAppointments(appointmentDTO.getStartAppointments().plusMinutes(30));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Schedule not found"));
        List<Appointment> appointments = appointmentRepository.findBySchedule(schedule);
        if(schedule.getMaxPatientPerDay() == appointments.size()){
            throw new NotCreateException("No free time found in the schedule");
        }
        appointments = appointmentRepository.findByStartAppointmentsAndDateAndSchedule(appointment.getStartAppointments(), appointment.getDate(), schedule);
        if(!appointments.isEmpty()){
            throw new NotCreateException("Time is already taken");
        }
        appointment.setSchedule(schedule);
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
    }
}
