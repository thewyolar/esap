package ru.javavlsu.kb.esap.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javavlsu.kb.esap.dto.AppointmentsCountByDayDTO;
import ru.javavlsu.kb.esap.dto.AppointmentDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.AppointmentResponseDTO;
import ru.javavlsu.kb.esap.mapper.AppointmentMapper;
import ru.javavlsu.kb.esap.model.*;
import ru.javavlsu.kb.esap.repository.AppointmentRepository;
import ru.javavlsu.kb.esap.repository.PatientRepository;
import ru.javavlsu.kb.esap.repository.ScheduleRepository;
import ru.javavlsu.kb.esap.exception.NotCreateException;
import ru.javavlsu.kb.esap.exception.NotFoundException;

import java.time.LocalDate;
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
    public void create(AppointmentDTO appointmentDTO, long scheduleId) throws NotCreateException {
        Appointment appointment = appointmentMapper.toAppointment(appointmentDTO);
        appointment.setEndAppointments(appointmentDTO.getStartAppointments().plusMinutes(30));
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NotFoundException("Schedule not found"));
        List<Appointment> appointments = appointmentRepository.findBySchedule(schedule);
        if (schedule.getMaxPatientPerDay() == appointments.size()) {
            throw new NotCreateException("No free time found in the schedule");
        }
        appointments = appointmentRepository.findByStartAppointmentsAndDateAndSchedule(appointment.getStartAppointments(), appointment.getDate(), schedule);
        if (!appointments.isEmpty()) {
            throw new NotCreateException("Time is already taken");
        }
        appointment.setSchedule(schedule);
        Patient patient = patientRepository.findById(appointmentDTO.getPatientId())
                .orElseThrow(() -> new NotFoundException("Patient not found"));
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);
    }

    @Transactional(readOnly = true)
    public List<AppointmentResponseDTO> getLatestAppointments(Integer count, Doctor doctor) {
        Pageable pageable = PageRequest.of(0, count);
        List<Appointment> appointments = appointmentRepository.findLatestAppointments(doctor, LocalDate.now(), pageable).stream().toList();
        return appointmentMapper.toAppointmentResponseDTOList(appointments);
    }

    @Transactional(readOnly = true)
    public List<AppointmentsCountByDayDTO> getAppointmentsCountByDay(Doctor doctor) {
        return appointmentRepository.countAppointmentsByDay(doctor.getClinic());
    }
}
