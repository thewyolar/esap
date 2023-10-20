package ru.javavlsu.kb.esap.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import jakarta.persistence.EntityManager;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.javavlsu.kb.esap.dto.DoctorDTO;
import ru.javavlsu.kb.esap.exception.NotFoundException;
import ru.javavlsu.kb.esap.mapper.DoctorMapper;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.repository.DoctorRepository;

@ContextConfiguration(classes = {DoctorService.class})
@ExtendWith(SpringExtension.class)
class DoctorServiceDiffblueTest {
    @MockBean
    private DoctorMapper doctorMapper;

    @MockBean
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    @MockBean
    private EntityManager entityManager;

    /**
     * Method under test: {@link DoctorService#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<Doctor> doctorList = new ArrayList<>();
        when(doctorRepository.findAll()).thenReturn(doctorList);
        List<Doctor> actualAll = doctorService.getAll();
        assertSame(doctorList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(doctorRepository).findAll();
    }

    /**
     * Method under test: {@link DoctorService#getAll()}
     */
    @Test
    void testGetAll2() {
        when(doctorRepository.findAll()).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> doctorService.getAll());
        verify(doctorRepository).findAll();
    }

    /**
     * Method under test: {@link DoctorService#getDoctorCountByClinic(Clinic)}
     */
    @Test
    void testGetDoctorCountByClinic() {
        when(doctorRepository.countDoctorByClinic(Mockito.<Clinic>any())).thenReturn(3);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertEquals(3, doctorService.getDoctorCountByClinic(clinic));
        verify(doctorRepository).countDoctorByClinic(Mockito.<Clinic>any());
    }

    /**
     * Method under test: {@link DoctorService#getDoctorCountByClinic(Clinic)}
     */
    @Test
    void testGetDoctorCountByClinic2() {
        when(doctorRepository.countDoctorByClinic(Mockito.<Clinic>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> doctorService.getDoctorCountByClinic(clinic));
        verify(doctorRepository).countDoctorByClinic(Mockito.<Clinic>any());
    }

    /**
     * Method under test: {@link DoctorService#getByClinic(Clinic, int)}
     */
    @Test
    void testGetByClinic() {
        PageImpl<DoctorDTO> pageImpl = new PageImpl<>(new ArrayList<>());
        when(doctorMapper.toDoctorDTOPage(Mockito.<Page<Doctor>>any())).thenReturn(pageImpl);
        when(doctorRepository.findByClinicOrderByIdAsc(Mockito.<Clinic>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        Page<DoctorDTO> actualByClinic = doctorService.getByClinic(clinic, 1);
        assertSame(pageImpl, actualByClinic);
        assertTrue(actualByClinic.toList().isEmpty());
        verify(doctorMapper).toDoctorDTOPage(Mockito.<Page<Doctor>>any());
        verify(doctorRepository).findByClinicOrderByIdAsc(Mockito.<Clinic>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link DoctorService#getByClinic(Clinic, int)}
     */
    @Test
    void testGetByClinic2() {
        when(doctorRepository.findByClinicOrderByIdAsc(Mockito.<Clinic>any(), Mockito.<Pageable>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> doctorService.getByClinic(clinic, 1));
        verify(doctorRepository).findByClinicOrderByIdAsc(Mockito.<Clinic>any(), Mockito.<Pageable>any());
    }

    /**
     * Method under test: {@link DoctorService#getDoctorsWithScheduleOnDate(Clinic, LocalDate)}
     */
    @Test
    void testGetDoctorsWithScheduleOnDate() {
        ArrayList<DoctorDTO> doctorDTOList = new ArrayList<>();
        when(doctorMapper.toDoctorDTOList(Mockito.<List<Doctor>>any())).thenReturn(doctorDTOList);
        when(doctorRepository.findByClinicAndSchedulesDateOrderByIdAsc(Mockito.<Clinic>any(), Mockito.<LocalDate>any()))
                .thenReturn(new ArrayList<>());

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        List<DoctorDTO> actualDoctorsWithScheduleOnDate = doctorService.getDoctorsWithScheduleOnDate(clinic,
                LocalDate.of(1970, 1, 1));
        assertSame(doctorDTOList, actualDoctorsWithScheduleOnDate);
        assertTrue(actualDoctorsWithScheduleOnDate.isEmpty());
        verify(doctorMapper).toDoctorDTOList(Mockito.<List<Doctor>>any());
        verify(doctorRepository).findByClinicAndSchedulesDateOrderByIdAsc(Mockito.<Clinic>any(),
                Mockito.<LocalDate>any());
    }

    /**
     * Method under test: {@link DoctorService#getDoctorsWithScheduleOnDate(Clinic, LocalDate)}
     */
    @Test
    void testGetDoctorsWithScheduleOnDate2() {
        when(doctorRepository.findByClinicAndSchedulesDateOrderByIdAsc(Mockito.<Clinic>any(), Mockito.<LocalDate>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class,
                () -> doctorService.getDoctorsWithScheduleOnDate(clinic, LocalDate.of(1970, 1, 1)));
        verify(doctorRepository).findByClinicAndSchedulesDateOrderByIdAsc(Mockito.<Clinic>any(),
                Mockito.<LocalDate>any());
    }

    /**
     * Method under test: {@link DoctorService#getById(long)}
     */
    @Test
    void testGetById() {
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
        assertSame(doctor, doctorService.getById(1L));
        verify(doctorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link DoctorService#getById(long)}
     */
    @Test
    void testGetById2() {
        Optional<Doctor> emptyResult = Optional.empty();
        when(doctorRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        assertThrows(NotFoundException.class, () -> doctorService.getById(1L));
        verify(doctorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link DoctorService#getById(long)}
     */
    @Test
    void testGetById3() {
        when(doctorRepository.findById(Mockito.<Long>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> doctorService.getById(1L));
        verify(doctorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link DoctorService#getByLogin(String)}
     */
    @Test
    void testGetByLogin() {
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
        when(doctorRepository.findByLogin(Mockito.<String>any())).thenReturn(ofResult);
        assertSame(doctor, doctorService.getByLogin("Login"));
        verify(doctorRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DoctorService#getByLogin(String)}
     */
    @Test
    void testGetByLogin2() {
        Optional<Doctor> emptyResult = Optional.empty();
        when(doctorRepository.findByLogin(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(NotFoundException.class, () -> doctorService.getByLogin("Login"));
        verify(doctorRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DoctorService#getByLogin(String)}
     */
    @Test
    void testGetByLogin3() {
        when(doctorRepository.findByLogin(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> doctorService.getByLogin("Login"));
        verify(doctorRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link DoctorService#refreshDoctor(Doctor)}
     */
    @Test
    void testRefreshDoctor() {
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
        when(entityManager.merge(Mockito.<Doctor>any())).thenReturn(doctor);

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
        assertSame(doctor, doctorService.refreshDoctor(doctor2));
        verify(entityManager).merge(Mockito.<Doctor>any());
    }

    /**
     * Method under test: {@link DoctorService#refreshDoctor(Doctor)}
     */
    @Test
    void testRefreshDoctor2() {
        when(entityManager.merge(Mockito.<Doctor>any())).thenThrow(new NotFoundException("An error occurred"));

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
        assertThrows(NotFoundException.class, () -> doctorService.refreshDoctor(doctor));
        verify(entityManager).merge(Mockito.<Doctor>any());
    }

    /**
     * Method under test: {@link DoctorService#save(Doctor)}
     */
    @Test
    void testSave() {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        ArrayList<Doctor> doctors = new ArrayList<>();
        clinic.setDoctors(doctors);
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
        when(doctorRepository.save(Mockito.<Doctor>any())).thenReturn(doctor);

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
        doctorService.save(doctor2);
        verify(doctorRepository).save(Mockito.<Doctor>any());
        assertEquals(clinic, doctor2.getClinic());
        assertEquals("Specialization", doctor2.getSpecialization());
        assertEquals(3, doctor2.getGender());
        assertEquals("iloveyou", doctor2.getPassword());
        assertEquals("Patronymic", doctor2.getPatronymic());
        assertEquals(1L, doctor2.getId().longValue());
        assertTrue(doctor2.getRole().isEmpty());
        assertEquals(doctors, doctor2.getSchedules());
        assertEquals("Jane", doctor2.getFirstName());
        assertEquals("Doe", doctor2.getLastName());
        assertEquals("Login", doctor2.getLogin());
    }

    /**
     * Method under test: {@link DoctorService#save(Doctor)}
     */
    @Test
    void testSave2() {
        when(doctorRepository.save(Mockito.<Doctor>any())).thenThrow(new NotFoundException("An error occurred"));

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
        assertThrows(NotFoundException.class, () -> doctorService.save(doctor));
        verify(doctorRepository).save(Mockito.<Doctor>any());
    }

    /**
     * Method under test: {@link DoctorService#update(Long, DoctorDTO)}
     */
    @Test
    void testUpdate() {
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
        when(doctorRepository.save(Mockito.<Doctor>any())).thenReturn(doctor2);
        when(doctorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Clinic clinic3 = new Clinic();
        clinic3.setAddress("42 Main St");
        clinic3.setDoctors(new ArrayList<>());
        clinic3.setId(1L);
        clinic3.setName("Name");
        clinic3.setPatients(new ArrayList<>());
        clinic3.setPhoneNumber("6625550144");

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setClinic(clinic3);
        doctorDTO.setFirstName("Jane");
        doctorDTO.setGender(3);
        doctorDTO.setId(1L);
        doctorDTO.setLastName("Doe");
        doctorDTO.setLogin("Login");
        doctorDTO.setPatronymic("Patronymic");
        doctorDTO.setSchedules(new ArrayList<>());
        doctorDTO.setSpecialization("Specialization");
        assertSame(doctor2, doctorService.update(1L, doctorDTO));
        verify(doctorRepository).save(Mockito.<Doctor>any());
        verify(doctorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link DoctorService#update(Long, DoctorDTO)}
     */
    @Test
    void testUpdate2() {
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
        when(doctorRepository.save(Mockito.<Doctor>any())).thenThrow(new NotFoundException("An error occurred"));
        when(doctorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Clinic clinic2 = new Clinic();
        clinic2.setAddress("42 Main St");
        clinic2.setDoctors(new ArrayList<>());
        clinic2.setId(1L);
        clinic2.setName("Name");
        clinic2.setPatients(new ArrayList<>());
        clinic2.setPhoneNumber("6625550144");

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setClinic(clinic2);
        doctorDTO.setFirstName("Jane");
        doctorDTO.setGender(3);
        doctorDTO.setId(1L);
        doctorDTO.setLastName("Doe");
        doctorDTO.setLogin("Login");
        doctorDTO.setPatronymic("Patronymic");
        doctorDTO.setSchedules(new ArrayList<>());
        doctorDTO.setSpecialization("Specialization");
        assertThrows(NotFoundException.class, () -> doctorService.update(1L, doctorDTO));
        verify(doctorRepository).save(Mockito.<Doctor>any());
        verify(doctorRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link DoctorService#update(Long, DoctorDTO)}
     */
    @Test
    void testUpdate3() {
        Optional<Doctor> emptyResult = Optional.empty();
        when(doctorRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setClinic(clinic);
        doctorDTO.setFirstName("Jane");
        doctorDTO.setGender(3);
        doctorDTO.setId(1L);
        doctorDTO.setLastName("Doe");
        doctorDTO.setLogin("Login");
        doctorDTO.setPatronymic("Patronymic");
        doctorDTO.setSchedules(new ArrayList<>());
        doctorDTO.setSpecialization("Specialization");
        assertThrows(NotFoundException.class, () -> doctorService.update(1L, doctorDTO));
        verify(doctorRepository).findById(Mockito.<Long>any());
    }
}

