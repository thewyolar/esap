package ru.javavlsu.kb.esap.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.javavlsu.kb.esap.dto.AppointmentDTO;
import ru.javavlsu.kb.esap.dto.AppointmentsCountByDayDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.AppointmentResponseDTO;
import ru.javavlsu.kb.esap.exception.NotCreateException;
import ru.javavlsu.kb.esap.mapper.AppointmentMapper;
import ru.javavlsu.kb.esap.model.Appointment;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.MedicalCard;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.model.Schedule;
import ru.javavlsu.kb.esap.repository.AppointmentRepository;
import ru.javavlsu.kb.esap.repository.PatientRepository;
import ru.javavlsu.kb.esap.repository.ScheduleRepository;

@ContextConfiguration(classes = {AppointmentService.class})
@ExtendWith(SpringExtension.class)
class AppointmentServiceDiffblueTest {
    @MockBean
    private AppointmentMapper appointmentMapper;

    @MockBean
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;

    @MockBean
    private PatientRepository patientRepository;

    @MockBean
    private ScheduleRepository scheduleRepository;

    /**
     * Method under test: {@link AppointmentService#create(AppointmentDTO, long)}
     */
    @Test
    void testCreate() throws NotCreateException {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

        Clinic clinic2 = new Clinic();
        clinic2.setAddress("42 Main St");
        clinic2.setDoctors(new ArrayList<>());
        clinic2.setId(1L);
        clinic2.setName("Name");
        clinic2.setPatients(new ArrayList<>());
        clinic2.setPhoneNumber("6625550144");

        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(1L);
        medicalCard.setMedicalRecord(new ArrayList<>());
        medicalCard.setPatient(new Patient());

        Patient patient = new Patient();
        patient.setAddress("42 Main St");
        patient.setAppointments(new ArrayList<>());
        patient.setBirthDate(LocalDate.of(1970, 1, 1));
        patient.setClinic(clinic2);
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender(3);
        patient.setId(1L);
        patient.setLastName("Doe");
        patient.setLogin("Login");
        patient.setMedicalCard(medicalCard);
        patient.setPassword("iloveyou");
        patient.setPatronymic("Patronymic");
        patient.setPhoneNumber("6625550144");
        patient.setRole(new HashSet<>());

        MedicalCard medicalCard2 = new MedicalCard();
        medicalCard2.setId(1L);
        medicalCard2.setMedicalRecord(new ArrayList<>());
        medicalCard2.setPatient(patient);

        Patient patient2 = new Patient();
        patient2.setAddress("42 Main St");
        patient2.setAppointments(new ArrayList<>());
        patient2.setBirthDate(LocalDate.of(1970, 1, 1));
        patient2.setClinic(clinic);
        patient2.setEmail("jane.doe@example.org");
        patient2.setFirstName("Jane");
        patient2.setGender(3);
        patient2.setId(1L);
        patient2.setLastName("Doe");
        patient2.setLogin("Login");
        patient2.setMedicalCard(medicalCard2);
        patient2.setPassword("iloveyou");
        patient2.setPatronymic("Patronymic");
        patient2.setPhoneNumber("6625550144");
        patient2.setRole(new HashSet<>());

        Clinic clinic3 = new Clinic();
        clinic3.setAddress("42 Main St");
        clinic3.setDoctors(new ArrayList<>());
        clinic3.setId(1L);
        clinic3.setName("Name");
        clinic3.setPatients(new ArrayList<>());
        clinic3.setPhoneNumber("6625550144");

        Doctor doctor = new Doctor();
        doctor.setClinic(clinic3);
        doctor.setFirstName("Jane");
        doctor.setGender(3);
        doctor.setId(1L);
        doctor.setLastName("Doe");
        doctor.setLogin("Login");
        doctor.setPassword("iloveyou");
        doctor.setPatronymic("Patronymic");
        doctor.setRole(new HashSet<>());
        doctor.setSchedules(new ArrayList<>());
        doctor.setSpecialization("Specialization");

        Schedule schedule = new Schedule();
        schedule.setAppointments(new ArrayList<>());
        schedule.setDate(LocalDate.of(1970, 1, 1));
        schedule.setDoctor(doctor);
        schedule.setEndDoctorAppointment(LocalTime.MIDNIGHT);
        schedule.setId(1L);
        schedule.setMaxPatientPerDay(3);
        schedule.setStartDoctorAppointment(LocalTime.MIDNIGHT);

        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(1970, 1, 1));
        appointment.setEndAppointments(LocalTime.MIDNIGHT);
        appointment.setId(1L);
        appointment.setPatient(patient2);
        appointment.setSchedule(schedule);
        appointment.setStartAppointments(LocalTime.MIDNIGHT);
        when(appointmentRepository.save(Mockito.<Appointment>any())).thenReturn(appointment);
        when(appointmentRepository.findBySchedule(Mockito.<Schedule>any())).thenReturn(new ArrayList<>());
        when(appointmentRepository.findByStartAppointmentsAndDateAndSchedule(Mockito.<LocalTime>any(),
                Mockito.<LocalDate>any(), Mockito.<Schedule>any())).thenReturn(new ArrayList<>());

        Clinic clinic4 = new Clinic();
        clinic4.setAddress("42 Main St");
        clinic4.setDoctors(new ArrayList<>());
        clinic4.setId(1L);
        clinic4.setName("Name");
        clinic4.setPatients(new ArrayList<>());
        clinic4.setPhoneNumber("6625550144");

        Doctor doctor2 = new Doctor();
        doctor2.setClinic(clinic4);
        doctor2.setFirstName("Jane");
        doctor2.setGender(3);
        doctor2.setId(1L);
        doctor2.setLastName("Doe");
        doctor2.setLogin("Login");
        doctor2.setPassword("iloveyou");
        doctor2.setPatronymic("Patronymic");
        doctor2.setRole(new HashSet<>());
        doctor2.setSchedules(new ArrayList<>());
        doctor2.setSpecialization("Specialization");

        Schedule schedule2 = new Schedule();
        schedule2.setAppointments(new ArrayList<>());
        schedule2.setDate(LocalDate.of(1970, 1, 1));
        schedule2.setDoctor(doctor2);
        schedule2.setEndDoctorAppointment(LocalTime.MIDNIGHT);
        schedule2.setId(1L);
        schedule2.setMaxPatientPerDay(3);
        schedule2.setStartDoctorAppointment(LocalTime.MIDNIGHT);
        Optional<Schedule> ofResult = Optional.of(schedule2);
        when(scheduleRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Clinic clinic5 = new Clinic();
        clinic5.setAddress("42 Main St");
        clinic5.setDoctors(new ArrayList<>());
        clinic5.setId(1L);
        clinic5.setName("Name");
        clinic5.setPatients(new ArrayList<>());
        clinic5.setPhoneNumber("6625550144");

        Clinic clinic6 = new Clinic();
        clinic6.setAddress("42 Main St");
        clinic6.setDoctors(new ArrayList<>());
        clinic6.setId(1L);
        clinic6.setName("Name");
        clinic6.setPatients(new ArrayList<>());
        clinic6.setPhoneNumber("6625550144");

        MedicalCard medicalCard3 = new MedicalCard();
        medicalCard3.setId(1L);
        medicalCard3.setMedicalRecord(new ArrayList<>());
        medicalCard3.setPatient(new Patient());

        Patient patient3 = new Patient();
        patient3.setAddress("42 Main St");
        patient3.setAppointments(new ArrayList<>());
        patient3.setBirthDate(LocalDate.of(1970, 1, 1));
        patient3.setClinic(clinic6);
        patient3.setEmail("jane.doe@example.org");
        patient3.setFirstName("Jane");
        patient3.setGender(3);
        patient3.setId(1L);
        patient3.setLastName("Doe");
        patient3.setLogin("Login");
        patient3.setMedicalCard(medicalCard3);
        patient3.setPassword("iloveyou");
        patient3.setPatronymic("Patronymic");
        patient3.setPhoneNumber("6625550144");
        patient3.setRole(new HashSet<>());

        MedicalCard medicalCard4 = new MedicalCard();
        medicalCard4.setId(1L);
        medicalCard4.setMedicalRecord(new ArrayList<>());
        medicalCard4.setPatient(patient3);

        Patient patient4 = new Patient();
        patient4.setAddress("42 Main St");
        patient4.setAppointments(new ArrayList<>());
        patient4.setBirthDate(LocalDate.of(1970, 1, 1));
        patient4.setClinic(clinic5);
        patient4.setEmail("jane.doe@example.org");
        patient4.setFirstName("Jane");
        patient4.setGender(3);
        patient4.setId(1L);
        patient4.setLastName("Doe");
        patient4.setLogin("Login");
        patient4.setMedicalCard(medicalCard4);
        patient4.setPassword("iloveyou");
        patient4.setPatronymic("Patronymic");
        patient4.setPhoneNumber("6625550144");
        patient4.setRole(new HashSet<>());
        Optional<Patient> ofResult2 = Optional.of(patient4);
        when(patientRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        Clinic clinic7 = new Clinic();
        clinic7.setAddress("42 Main St");
        clinic7.setDoctors(new ArrayList<>());
        clinic7.setId(1L);
        clinic7.setName("Name");
        clinic7.setPatients(new ArrayList<>());
        clinic7.setPhoneNumber("6625550144");

        Clinic clinic8 = new Clinic();
        clinic8.setAddress("42 Main St");
        clinic8.setDoctors(new ArrayList<>());
        clinic8.setId(1L);
        clinic8.setName("Name");
        clinic8.setPatients(new ArrayList<>());
        clinic8.setPhoneNumber("6625550144");

        MedicalCard medicalCard5 = new MedicalCard();
        medicalCard5.setId(1L);
        medicalCard5.setMedicalRecord(new ArrayList<>());
        medicalCard5.setPatient(new Patient());

        Patient patient5 = new Patient();
        patient5.setAddress("42 Main St");
        patient5.setAppointments(new ArrayList<>());
        patient5.setBirthDate(LocalDate.of(1970, 1, 1));
        patient5.setClinic(clinic8);
        patient5.setEmail("jane.doe@example.org");
        patient5.setFirstName("Jane");
        patient5.setGender(3);
        patient5.setId(1L);
        patient5.setLastName("Doe");
        patient5.setLogin("Login");
        patient5.setMedicalCard(medicalCard5);
        patient5.setPassword("iloveyou");
        patient5.setPatronymic("Patronymic");
        patient5.setPhoneNumber("6625550144");
        patient5.setRole(new HashSet<>());

        MedicalCard medicalCard6 = new MedicalCard();
        medicalCard6.setId(1L);
        medicalCard6.setMedicalRecord(new ArrayList<>());
        medicalCard6.setPatient(patient5);

        Patient patient6 = new Patient();
        patient6.setAddress("42 Main St");
        patient6.setAppointments(new ArrayList<>());
        patient6.setBirthDate(LocalDate.of(1970, 1, 1));
        patient6.setClinic(clinic7);
        patient6.setEmail("jane.doe@example.org");
        patient6.setFirstName("Jane");
        patient6.setGender(3);
        patient6.setId(1L);
        patient6.setLastName("Doe");
        patient6.setLogin("Login");
        patient6.setMedicalCard(medicalCard6);
        patient6.setPassword("iloveyou");
        patient6.setPatronymic("Patronymic");
        patient6.setPhoneNumber("6625550144");
        patient6.setRole(new HashSet<>());

        Clinic clinic9 = new Clinic();
        clinic9.setAddress("42 Main St");
        clinic9.setDoctors(new ArrayList<>());
        clinic9.setId(1L);
        clinic9.setName("Name");
        clinic9.setPatients(new ArrayList<>());
        clinic9.setPhoneNumber("6625550144");

        Doctor doctor3 = new Doctor();
        doctor3.setClinic(clinic9);
        doctor3.setFirstName("Jane");
        doctor3.setGender(3);
        doctor3.setId(1L);
        doctor3.setLastName("Doe");
        doctor3.setLogin("Login");
        doctor3.setPassword("iloveyou");
        doctor3.setPatronymic("Patronymic");
        doctor3.setRole(new HashSet<>());
        doctor3.setSchedules(new ArrayList<>());
        doctor3.setSpecialization("Specialization");

        Schedule schedule3 = new Schedule();
        schedule3.setAppointments(new ArrayList<>());
        schedule3.setDate(LocalDate.of(1970, 1, 1));
        schedule3.setDoctor(doctor3);
        schedule3.setEndDoctorAppointment(LocalTime.MIDNIGHT);
        schedule3.setId(1L);
        schedule3.setMaxPatientPerDay(3);
        schedule3.setStartDoctorAppointment(LocalTime.MIDNIGHT);

        Appointment appointment2 = new Appointment();
        appointment2.setDate(LocalDate.of(1970, 1, 1));
        appointment2.setEndAppointments(LocalTime.MIDNIGHT);
        appointment2.setId(1L);
        appointment2.setPatient(patient6);
        appointment2.setSchedule(schedule3);
        appointment2.setStartAppointments(LocalTime.MIDNIGHT);
        when(appointmentMapper.toAppointment(Mockito.<AppointmentDTO>any())).thenReturn(appointment2);

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDate(LocalDate.of(1970, 1, 1));
        appointmentDTO.setPatientId(1L);
        appointmentDTO.setStartAppointments(LocalTime.MIDNIGHT);
        appointmentService.create(appointmentDTO, 1L);
        verify(appointmentRepository).save(Mockito.<Appointment>any());
        verify(appointmentRepository).findBySchedule(Mockito.<Schedule>any());
        verify(appointmentRepository).findByStartAppointmentsAndDateAndSchedule(Mockito.<LocalTime>any(),
                Mockito.<LocalDate>any(), Mockito.<Schedule>any());
        verify(scheduleRepository).findById(Mockito.<Long>any());
        verify(patientRepository).findById(Mockito.<Long>any());
        verify(appointmentMapper).toAppointment(Mockito.<AppointmentDTO>any());
    }

    /**
     * Method under test: {@link AppointmentService#create(AppointmentDTO, long)}
     */
    @Test
    void testCreate2() throws NotCreateException {
        when(appointmentRepository.save(Mockito.<Appointment>any())).thenThrow(new NotCreateException("Msg"));
        when(appointmentRepository.findBySchedule(Mockito.<Schedule>any())).thenReturn(new ArrayList<>());
        when(appointmentRepository.findByStartAppointmentsAndDateAndSchedule(Mockito.<LocalTime>any(),
                Mockito.<LocalDate>any(), Mockito.<Schedule>any())).thenReturn(new ArrayList<>());

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

        Doctor doctor = new Doctor();
        doctor.setClinic(clinic);
        doctor.setFirstName("Jane");
        doctor.setGender(3);
        doctor.setId(1L);
        doctor.setLastName("Doe");
        doctor.setLogin("Login");
        doctor.setPassword("iloveyou");
        doctor.setPatronymic("Patronymic");
        doctor.setRole(new HashSet<>());
        doctor.setSchedules(new ArrayList<>());
        doctor.setSpecialization("Specialization");

        Schedule schedule = new Schedule();
        schedule.setAppointments(new ArrayList<>());
        schedule.setDate(LocalDate.of(1970, 1, 1));
        schedule.setDoctor(doctor);
        schedule.setEndDoctorAppointment(LocalTime.MIDNIGHT);
        schedule.setId(1L);
        schedule.setMaxPatientPerDay(3);
        schedule.setStartDoctorAppointment(LocalTime.MIDNIGHT);
        Optional<Schedule> ofResult = Optional.of(schedule);
        when(scheduleRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Clinic clinic2 = new Clinic();
        clinic2.setAddress("42 Main St");
        clinic2.setDoctors(new ArrayList<>());
        clinic2.setId(1L);
        clinic2.setName("Name");
        clinic2.setPatients(new ArrayList<>());
        clinic2.setPhoneNumber("6625550144");

        Clinic clinic3 = new Clinic();
        clinic3.setAddress("42 Main St");
        clinic3.setDoctors(new ArrayList<>());
        clinic3.setId(1L);
        clinic3.setName("Name");
        clinic3.setPatients(new ArrayList<>());
        clinic3.setPhoneNumber("6625550144");

        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(1L);
        medicalCard.setMedicalRecord(new ArrayList<>());
        medicalCard.setPatient(new Patient());

        Patient patient = new Patient();
        patient.setAddress("42 Main St");
        patient.setAppointments(new ArrayList<>());
        patient.setBirthDate(LocalDate.of(1970, 1, 1));
        patient.setClinic(clinic3);
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender(3);
        patient.setId(1L);
        patient.setLastName("Doe");
        patient.setLogin("Login");
        patient.setMedicalCard(medicalCard);
        patient.setPassword("iloveyou");
        patient.setPatronymic("Patronymic");
        patient.setPhoneNumber("6625550144");
        patient.setRole(new HashSet<>());

        MedicalCard medicalCard2 = new MedicalCard();
        medicalCard2.setId(1L);
        medicalCard2.setMedicalRecord(new ArrayList<>());
        medicalCard2.setPatient(patient);

        Patient patient2 = new Patient();
        patient2.setAddress("42 Main St");
        patient2.setAppointments(new ArrayList<>());
        patient2.setBirthDate(LocalDate.of(1970, 1, 1));
        patient2.setClinic(clinic2);
        patient2.setEmail("jane.doe@example.org");
        patient2.setFirstName("Jane");
        patient2.setGender(3);
        patient2.setId(1L);
        patient2.setLastName("Doe");
        patient2.setLogin("Login");
        patient2.setMedicalCard(medicalCard2);
        patient2.setPassword("iloveyou");
        patient2.setPatronymic("Patronymic");
        patient2.setPhoneNumber("6625550144");
        patient2.setRole(new HashSet<>());
        Optional<Patient> ofResult2 = Optional.of(patient2);
        when(patientRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        Clinic clinic4 = new Clinic();
        clinic4.setAddress("42 Main St");
        clinic4.setDoctors(new ArrayList<>());
        clinic4.setId(1L);
        clinic4.setName("Name");
        clinic4.setPatients(new ArrayList<>());
        clinic4.setPhoneNumber("6625550144");

        Clinic clinic5 = new Clinic();
        clinic5.setAddress("42 Main St");
        clinic5.setDoctors(new ArrayList<>());
        clinic5.setId(1L);
        clinic5.setName("Name");
        clinic5.setPatients(new ArrayList<>());
        clinic5.setPhoneNumber("6625550144");

        MedicalCard medicalCard3 = new MedicalCard();
        medicalCard3.setId(1L);
        medicalCard3.setMedicalRecord(new ArrayList<>());
        medicalCard3.setPatient(new Patient());

        Patient patient3 = new Patient();
        patient3.setAddress("42 Main St");
        patient3.setAppointments(new ArrayList<>());
        patient3.setBirthDate(LocalDate.of(1970, 1, 1));
        patient3.setClinic(clinic5);
        patient3.setEmail("jane.doe@example.org");
        patient3.setFirstName("Jane");
        patient3.setGender(3);
        patient3.setId(1L);
        patient3.setLastName("Doe");
        patient3.setLogin("Login");
        patient3.setMedicalCard(medicalCard3);
        patient3.setPassword("iloveyou");
        patient3.setPatronymic("Patronymic");
        patient3.setPhoneNumber("6625550144");
        patient3.setRole(new HashSet<>());

        MedicalCard medicalCard4 = new MedicalCard();
        medicalCard4.setId(1L);
        medicalCard4.setMedicalRecord(new ArrayList<>());
        medicalCard4.setPatient(patient3);

        Patient patient4 = new Patient();
        patient4.setAddress("42 Main St");
        patient4.setAppointments(new ArrayList<>());
        patient4.setBirthDate(LocalDate.of(1970, 1, 1));
        patient4.setClinic(clinic4);
        patient4.setEmail("jane.doe@example.org");
        patient4.setFirstName("Jane");
        patient4.setGender(3);
        patient4.setId(1L);
        patient4.setLastName("Doe");
        patient4.setLogin("Login");
        patient4.setMedicalCard(medicalCard4);
        patient4.setPassword("iloveyou");
        patient4.setPatronymic("Patronymic");
        patient4.setPhoneNumber("6625550144");
        patient4.setRole(new HashSet<>());

        Clinic clinic6 = new Clinic();
        clinic6.setAddress("42 Main St");
        clinic6.setDoctors(new ArrayList<>());
        clinic6.setId(1L);
        clinic6.setName("Name");
        clinic6.setPatients(new ArrayList<>());
        clinic6.setPhoneNumber("6625550144");

        Doctor doctor2 = new Doctor();
        doctor2.setClinic(clinic6);
        doctor2.setFirstName("Jane");
        doctor2.setGender(3);
        doctor2.setId(1L);
        doctor2.setLastName("Doe");
        doctor2.setLogin("Login");
        doctor2.setPassword("iloveyou");
        doctor2.setPatronymic("Patronymic");
        doctor2.setRole(new HashSet<>());
        doctor2.setSchedules(new ArrayList<>());
        doctor2.setSpecialization("Specialization");

        Schedule schedule2 = new Schedule();
        schedule2.setAppointments(new ArrayList<>());
        schedule2.setDate(LocalDate.of(1970, 1, 1));
        schedule2.setDoctor(doctor2);
        schedule2.setEndDoctorAppointment(LocalTime.MIDNIGHT);
        schedule2.setId(1L);
        schedule2.setMaxPatientPerDay(3);
        schedule2.setStartDoctorAppointment(LocalTime.MIDNIGHT);

        Appointment appointment = new Appointment();
        appointment.setDate(LocalDate.of(1970, 1, 1));
        appointment.setEndAppointments(LocalTime.MIDNIGHT);
        appointment.setId(1L);
        appointment.setPatient(patient4);
        appointment.setSchedule(schedule2);
        appointment.setStartAppointments(LocalTime.MIDNIGHT);
        when(appointmentMapper.toAppointment(Mockito.<AppointmentDTO>any())).thenReturn(appointment);

        AppointmentDTO appointmentDTO = new AppointmentDTO();
        appointmentDTO.setDate(LocalDate.of(1970, 1, 1));
        appointmentDTO.setPatientId(1L);
        appointmentDTO.setStartAppointments(LocalTime.MIDNIGHT);
        assertThrows(NotCreateException.class, () -> appointmentService.create(appointmentDTO, 1L));
        verify(appointmentRepository).save(Mockito.<Appointment>any());
        verify(appointmentRepository).findBySchedule(Mockito.<Schedule>any());
        verify(appointmentRepository).findByStartAppointmentsAndDateAndSchedule(Mockito.<LocalTime>any(),
                Mockito.<LocalDate>any(), Mockito.<Schedule>any());
        verify(scheduleRepository).findById(Mockito.<Long>any());
        verify(patientRepository).findById(Mockito.<Long>any());
        verify(appointmentMapper).toAppointment(Mockito.<AppointmentDTO>any());
    }

    /**
     * Method under test: {@link AppointmentService#getLatestAppointments(Integer, Doctor)}
     */
    @Test
    void testGetLatestAppointments() {
        when(appointmentRepository.findLatestAppointments(Mockito.<Doctor>any(), Mockito.<LocalDate>any(),
                Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        ArrayList<AppointmentResponseDTO> appointmentResponseDTOList = new ArrayList<>();
        when(appointmentMapper.toAppointmentResponseDTOList(Mockito.<List<Appointment>>any()))
                .thenReturn(appointmentResponseDTOList);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

        Doctor doctor = new Doctor();
        doctor.setClinic(clinic);
        doctor.setFirstName("Jane");
        doctor.setGender(3);
        doctor.setId(1L);
        doctor.setLastName("Doe");
        doctor.setLogin("Login");
        doctor.setPassword("iloveyou");
        doctor.setPatronymic("Patronymic");
        doctor.setRole(new HashSet<>());
        doctor.setSchedules(new ArrayList<>());
        doctor.setSpecialization("Specialization");
        List<AppointmentResponseDTO> actualLatestAppointments = appointmentService.getLatestAppointments(3, doctor);
        assertSame(appointmentResponseDTOList, actualLatestAppointments);
        assertTrue(actualLatestAppointments.isEmpty());
        verify(appointmentRepository).findLatestAppointments(Mockito.<Doctor>any(), Mockito.<LocalDate>any(),
                Mockito.<Pageable>any());
        verify(appointmentMapper).toAppointmentResponseDTOList(Mockito.<List<Appointment>>any());
    }

    /**
     * Method under test: {@link AppointmentService#getLatestAppointments(Integer, Doctor)}
     */
    @Test
    void testGetLatestAppointments2() {
        when(appointmentRepository.findLatestAppointments(Mockito.<Doctor>any(), Mockito.<LocalDate>any(),
                Mockito.<Pageable>any())).thenReturn(new PageImpl<>(new ArrayList<>()));
        when(appointmentMapper.toAppointmentResponseDTOList(Mockito.<List<Appointment>>any()))
                .thenThrow(new NotCreateException("Msg"));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

        Doctor doctor = new Doctor();
        doctor.setClinic(clinic);
        doctor.setFirstName("Jane");
        doctor.setGender(3);
        doctor.setId(1L);
        doctor.setLastName("Doe");
        doctor.setLogin("Login");
        doctor.setPassword("iloveyou");
        doctor.setPatronymic("Patronymic");
        doctor.setRole(new HashSet<>());
        doctor.setSchedules(new ArrayList<>());
        doctor.setSpecialization("Specialization");
        assertThrows(NotCreateException.class, () -> appointmentService.getLatestAppointments(3, doctor));
        verify(appointmentRepository).findLatestAppointments(Mockito.<Doctor>any(), Mockito.<LocalDate>any(),
                Mockito.<Pageable>any());
        verify(appointmentMapper).toAppointmentResponseDTOList(Mockito.<List<Appointment>>any());
    }

    /**
     * Method under test: {@link AppointmentService#getAppointmentsCountByDay(Doctor)}
     */
    @Test
    void testGetAppointmentsCountByDay() {
        ArrayList<AppointmentsCountByDayDTO> appointmentsCountByDayDTOList = new ArrayList<>();
        when(appointmentRepository.countAppointmentsByDay(Mockito.<Clinic>any()))
                .thenReturn(appointmentsCountByDayDTOList);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

        Doctor doctor = new Doctor();
        doctor.setClinic(clinic);
        doctor.setFirstName("Jane");
        doctor.setGender(3);
        doctor.setId(1L);
        doctor.setLastName("Doe");
        doctor.setLogin("Login");
        doctor.setPassword("iloveyou");
        doctor.setPatronymic("Patronymic");
        doctor.setRole(new HashSet<>());
        doctor.setSchedules(new ArrayList<>());
        doctor.setSpecialization("Specialization");
        List<AppointmentsCountByDayDTO> actualAppointmentsCountByDay = appointmentService
                .getAppointmentsCountByDay(doctor);
        assertSame(appointmentsCountByDayDTOList, actualAppointmentsCountByDay);
        assertTrue(actualAppointmentsCountByDay.isEmpty());
        verify(appointmentRepository).countAppointmentsByDay(Mockito.<Clinic>any());
    }

    /**
     * Method under test: {@link AppointmentService#getAppointmentsCountByDay(Doctor)}
     */
    @Test
    void testGetAppointmentsCountByDay2() {
        when(appointmentRepository.countAppointmentsByDay(Mockito.<Clinic>any()))
                .thenThrow(new NotCreateException("Msg"));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

        Doctor doctor = new Doctor();
        doctor.setClinic(clinic);
        doctor.setFirstName("Jane");
        doctor.setGender(3);
        doctor.setId(1L);
        doctor.setLastName("Doe");
        doctor.setLogin("Login");
        doctor.setPassword("iloveyou");
        doctor.setPatronymic("Patronymic");
        doctor.setRole(new HashSet<>());
        doctor.setSchedules(new ArrayList<>());
        doctor.setSpecialization("Specialization");
        assertThrows(NotCreateException.class, () -> appointmentService.getAppointmentsCountByDay(doctor));
        verify(appointmentRepository).countAppointmentsByDay(Mockito.<Clinic>any());
    }
}

