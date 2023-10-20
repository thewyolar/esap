package ru.javavlsu.kb.esap.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.javavlsu.kb.esap.dto.ScheduleDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.ScheduleResponseDTO;
import ru.javavlsu.kb.esap.exception.NotCreateException;
import ru.javavlsu.kb.esap.exception.NotFoundException;
import ru.javavlsu.kb.esap.mapper.ScheduleMapper;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.Schedule;
import ru.javavlsu.kb.esap.repository.DoctorRepository;
import ru.javavlsu.kb.esap.repository.ScheduleRepository;

@ContextConfiguration(classes = {ScheduleService.class})
@ExtendWith(SpringExtension.class)
class ScheduleServiceDiffblueTest {
    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private ScheduleMapper scheduleMapper;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ScheduleService scheduleService;

    /**
     * Method under test: {@link ScheduleService#create(ScheduleDTO)}
     */
    @Test
    void testCreate() throws NotCreateException {
        when(scheduleRepository.existsByDateAndDoctor(Mockito.<LocalDate>any(), Mockito.<Doctor>any())).thenReturn(true);

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
        Optional<Doctor> ofResult = Optional.of(doctor);
        when(doctorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate(LocalDate.of(1970, 1, 1));
        scheduleDTO.setDoctorId(1L);
        scheduleDTO.setEndDoctorAppointment(LocalTime.MIDNIGHT);
        scheduleDTO.setStartDoctorAppointment(LocalTime.MIDNIGHT);
        assertThrows(NotCreateException.class, () -> scheduleService.create(scheduleDTO));
        verify(scheduleRepository).existsByDateAndDoctor(Mockito.<LocalDate>any(), Mockito.<Doctor>any());
        verify(doctorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ScheduleService#create(ScheduleDTO)}
     */
    @Test
    void testCreate2() throws NotCreateException {
        when(scheduleRepository.existsByDateAndDoctor(Mockito.<LocalDate>any(), Mockito.<Doctor>any()))
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
        Optional<Doctor> ofResult = Optional.of(doctor);
        when(doctorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate(LocalDate.of(1970, 1, 1));
        scheduleDTO.setDoctorId(1L);
        scheduleDTO.setEndDoctorAppointment(LocalTime.MIDNIGHT);
        scheduleDTO.setStartDoctorAppointment(LocalTime.MIDNIGHT);
        assertThrows(NotCreateException.class, () -> scheduleService.create(scheduleDTO));
        verify(scheduleRepository).existsByDateAndDoctor(Mockito.<LocalDate>any(), Mockito.<Doctor>any());
        verify(doctorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ScheduleService#create(ScheduleDTO)}
     */
    @Test
    void testCreate3() throws NotCreateException {
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
        when(scheduleRepository.existsByDateAndDoctor(Mockito.<LocalDate>any(), Mockito.<Doctor>any())).thenReturn(false);
        when(scheduleRepository.save(Mockito.<Schedule>any())).thenReturn(schedule);

        Clinic clinic2 = new Clinic();
        clinic2.setAddress("42 Main St");
        clinic2.setDoctors(new ArrayList<>());
        clinic2.setId(1L);
        clinic2.setName("Name");
        clinic2.setPatients(new ArrayList<>());
        clinic2.setPhoneNumber("6625550144");

        Doctor doctor2 = new Doctor();
        doctor2.setClinic(clinic2);
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
        when(scheduleMapper.toSchedule(Mockito.<ScheduleDTO>any())).thenReturn(schedule2);

        Clinic clinic3 = new Clinic();
        clinic3.setAddress("42 Main St");
        clinic3.setDoctors(new ArrayList<>());
        clinic3.setId(1L);
        clinic3.setName("Name");
        clinic3.setPatients(new ArrayList<>());
        clinic3.setPhoneNumber("6625550144");

        Doctor doctor3 = new Doctor();
        doctor3.setClinic(clinic3);
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
        Optional<Doctor> ofResult = Optional.of(doctor3);
        when(doctorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setDate(LocalDate.of(1970, 1, 1));
        scheduleDTO.setDoctorId(1L);
        scheduleDTO.setEndDoctorAppointment(LocalTime.MIDNIGHT);
        scheduleDTO.setStartDoctorAppointment(LocalTime.MIDNIGHT);
        assertSame(schedule, scheduleService.create(scheduleDTO));
        verify(scheduleRepository).existsByDateAndDoctor(Mockito.<LocalDate>any(), Mockito.<Doctor>any());
        verify(scheduleRepository).save(Mockito.<Schedule>any());
        verify(scheduleMapper).toSchedule(Mockito.<ScheduleDTO>any());
        verify(doctorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ScheduleService#getAllByDoctorId(Long)}
     */
    @Test
    void testGetAllByDoctorId() {
        ArrayList<Schedule> scheduleList = new ArrayList<>();
        when(scheduleRepository.findAllByDoctorId(Mockito.<Long>any())).thenReturn(scheduleList);
        List<Schedule> actualAllByDoctorId = scheduleService.getAllByDoctorId(1L);
        assertSame(scheduleList, actualAllByDoctorId);
        assertTrue(actualAllByDoctorId.isEmpty());
        verify(scheduleRepository).findAllByDoctorId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ScheduleService#getAllByDoctorId(Long)}
     */
    @Test
    void testGetAllByDoctorId2() {
        when(scheduleRepository.findAllByDoctorId(Mockito.<Long>any())).thenThrow(new NotCreateException("Msg"));
        assertThrows(NotCreateException.class, () -> scheduleService.getAllByDoctorId(1L));
        verify(scheduleRepository).findAllByDoctorId(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link ScheduleService#getByIdAndDoctor(long, Doctor)}
     */
    @Test
    void testGetByIdAndDoctor() {
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
        when(scheduleRepository.findByIdAndDoctorOrderByAppointmentStartAppointmentsAsc(Mockito.<Long>any(),
                Mockito.<Doctor>any())).thenReturn(ofResult);

        Clinic clinic2 = new Clinic();
        clinic2.setAddress("42 Main St");
        clinic2.setDoctors(new ArrayList<>());
        clinic2.setId(1L);
        clinic2.setName("Name");
        clinic2.setPatients(new ArrayList<>());
        clinic2.setPhoneNumber("6625550144");

        Doctor doctor2 = new Doctor();
        doctor2.setClinic(clinic2);
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
        assertSame(schedule, scheduleService.getByIdAndDoctor(1L, doctor2));
        verify(scheduleRepository).findByIdAndDoctorOrderByAppointmentStartAppointmentsAsc(Mockito.<Long>any(),
                Mockito.<Doctor>any());
    }

    /**
     * Method under test: {@link ScheduleService#getByIdAndDoctor(long, Doctor)}
     */
    @Test
    void testGetByIdAndDoctor2() {
        Optional<Schedule> emptyResult = Optional.empty();
        when(scheduleRepository.findByIdAndDoctorOrderByAppointmentStartAppointmentsAsc(Mockito.<Long>any(),
                Mockito.<Doctor>any())).thenReturn(emptyResult);

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
        assertThrows(NotFoundException.class, () -> scheduleService.getByIdAndDoctor(1L, doctor));
        verify(scheduleRepository).findByIdAndDoctorOrderByAppointmentStartAppointmentsAsc(Mockito.<Long>any(),
                Mockito.<Doctor>any());
    }

    /**
     * Method under test: {@link ScheduleService#getByIdAndDoctor(long, Doctor)}
     */
    @Test
    void testGetByIdAndDoctor3() {
        when(scheduleRepository.findByIdAndDoctorOrderByAppointmentStartAppointmentsAsc(Mockito.<Long>any(),
                Mockito.<Doctor>any())).thenThrow(new NotCreateException("Schedule not found"));

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
        assertThrows(NotCreateException.class, () -> scheduleService.getByIdAndDoctor(1L, doctor));
        verify(scheduleRepository).findByIdAndDoctorOrderByAppointmentStartAppointmentsAsc(Mockito.<Long>any(),
                Mockito.<Doctor>any());
    }

    /**
     * Method under test: {@link ScheduleService#getSchedulesByDay(LocalDate, Clinic)}
     */
    @Test
    void testGetSchedulesByDay() {
        when(scheduleRepository.findAllByClinic(Mockito.<LocalDate>any(), Mockito.<Clinic>any()))
                .thenReturn(new ArrayList<>());
        ArrayList<ScheduleResponseDTO> scheduleResponseDTOList = new ArrayList<>();
        when(scheduleMapper.toScheduleResponseDTOList(Mockito.<List<Schedule>>any())).thenReturn(scheduleResponseDTOList);
        LocalDate date = LocalDate.of(1970, 1, 1);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        List<ScheduleResponseDTO> actualSchedulesByDay = scheduleService.getSchedulesByDay(date, clinic);
        assertSame(scheduleResponseDTOList, actualSchedulesByDay);
        assertTrue(actualSchedulesByDay.isEmpty());
        verify(scheduleRepository).findAllByClinic(Mockito.<LocalDate>any(), Mockito.<Clinic>any());
        verify(scheduleMapper).toScheduleResponseDTOList(Mockito.<List<Schedule>>any());
    }

    /**
     * Method under test: {@link ScheduleService#getSchedulesByDay(LocalDate, Clinic)}
     */
    @Test
    void testGetSchedulesByDay2() {
        when(scheduleRepository.findAllByClinic(Mockito.<LocalDate>any(), Mockito.<Clinic>any()))
                .thenReturn(new ArrayList<>());
        when(scheduleMapper.toScheduleResponseDTOList(Mockito.<List<Schedule>>any()))
                .thenThrow(new NotCreateException("Msg"));
        LocalDate date = LocalDate.of(1970, 1, 1);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertThrows(NotCreateException.class, () -> scheduleService.getSchedulesByDay(date, clinic));
        verify(scheduleRepository).findAllByClinic(Mockito.<LocalDate>any(), Mockito.<Clinic>any());
        verify(scheduleMapper).toScheduleResponseDTOList(Mockito.<List<Schedule>>any());
    }

    /**
     * Method under test: {@link ScheduleService#getSchedulesByDay(LocalDate, Clinic)}
     */
    @Test
    void testGetSchedulesByDay3() {
        when(scheduleRepository.findAllByClinic(Mockito.<LocalDate>any(), Mockito.<Clinic>any()))
                .thenReturn(new ArrayList<>());
        ArrayList<ScheduleResponseDTO> scheduleResponseDTOList = new ArrayList<>();
        when(scheduleMapper.toScheduleResponseDTOList(Mockito.<List<Schedule>>any())).thenReturn(scheduleResponseDTOList);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        List<ScheduleResponseDTO> actualSchedulesByDay = scheduleService.getSchedulesByDay(null, clinic);
        assertSame(scheduleResponseDTOList, actualSchedulesByDay);
        assertTrue(actualSchedulesByDay.isEmpty());
        verify(scheduleRepository).findAllByClinic(Mockito.<LocalDate>any(), Mockito.<Clinic>any());
        verify(scheduleMapper).toScheduleResponseDTOList(Mockito.<List<Schedule>>any());
    }

    /**
     * Method under test: {@link ScheduleService#deleteOverdueSchedules()}
     */
    @Test
    void testDeleteOverdueSchedules() {
        doNothing().when(scheduleRepository).deleteScheduleByDateBefore(Mockito.<LocalDate>any());
        scheduleService.deleteOverdueSchedules();
        verify(scheduleRepository).deleteScheduleByDateBefore(Mockito.<LocalDate>any());
    }

    /**
     * Method under test: {@link ScheduleService#deleteOverdueSchedules()}
     */
    @Test
    void testDeleteOverdueSchedules2() {
        doThrow(new NotCreateException("Msg")).when(scheduleRepository)
                .deleteScheduleByDateBefore(Mockito.<LocalDate>any());
        assertThrows(NotCreateException.class, () -> scheduleService.deleteOverdueSchedules());
        verify(scheduleRepository).deleteScheduleByDateBefore(Mockito.<LocalDate>any());
    }
}

