package ru.javavlsu.kb.esap.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.javavlsu.kb.esap.dto.PatientDTO;
import ru.javavlsu.kb.esap.dto.PatientStatisticsByAgeDTO;
import ru.javavlsu.kb.esap.dto.PatientStatisticsByGenderDTO;
import ru.javavlsu.kb.esap.dto.ScheduleResponseDTO.PatientResponseDTO;
import ru.javavlsu.kb.esap.exception.NotFoundException;
import ru.javavlsu.kb.esap.mapper.PatientMapper;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.MedicalCard;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.model.Role;
import ru.javavlsu.kb.esap.repository.MedicalCardRepository;
import ru.javavlsu.kb.esap.repository.PatientRepository;
import ru.javavlsu.kb.esap.repository.RoleRepository;
import ru.javavlsu.kb.esap.util.LoginPasswordGenerator;
import ru.javavlsu.kb.esap.util.PatientWithPassword;

@ContextConfiguration(classes = {PatientService.class})
@ExtendWith(SpringExtension.class)
class PatientServiceDiffblueTest {
    @MockBean
    private LoginPasswordGenerator loginPasswordGenerator;

    @MockBean
    private MedicalCardRepository medicalCardRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private PatientMapper patientMapper;

    @MockBean
    private PatientRepository patientRepository;

    @Autowired
    private PatientService patientService;

    @MockBean
    private RoleRepository roleRepository;

    /**
     * Method under test: {@link PatientService#getAll()}
     */
    @Test
    void testGetAll() {
        when(patientRepository.findAll()).thenReturn(new ArrayList<>());
        ArrayList<PatientResponseDTO> patientResponseDTOList = new ArrayList<>();
        when(patientMapper.toPatientResponseDTOList(Mockito.<List<Patient>>any())).thenReturn(patientResponseDTOList);
        List<PatientResponseDTO> actualAll = patientService.getAll();
        assertSame(patientResponseDTOList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(patientRepository).findAll();
        verify(patientMapper).toPatientResponseDTOList(Mockito.<List<Patient>>any());
    }

    /**
     * Method under test: {@link PatientService#getAll()}
     */
    @Test
    void testGetAll2() {
        when(patientRepository.findAll()).thenReturn(new ArrayList<>());
        when(patientMapper.toPatientResponseDTOList(Mockito.<List<Patient>>any()))
                .thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> patientService.getAll());
        verify(patientRepository).findAll();
        verify(patientMapper).toPatientResponseDTOList(Mockito.<List<Patient>>any());
    }

    /**
     * Method under test: {@link PatientService#getPatientCountByClinic(Clinic)}
     */
    @Test
    void testGetPatientCountByClinic() {
        when(patientRepository.countPatientByClinic(Mockito.<Clinic>any())).thenReturn(3);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertEquals(3, patientService.getPatientCountByClinic(clinic));
        verify(patientRepository).countPatientByClinic(Mockito.<Clinic>any());
    }

    /**
     * Method under test: {@link PatientService#getPatientCountByClinic(Clinic)}
     */
    @Test
    void testGetPatientCountByClinic2() {
        when(patientRepository.countPatientByClinic(Mockito.<Clinic>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> patientService.getPatientCountByClinic(clinic));
        verify(patientRepository).countPatientByClinic(Mockito.<Clinic>any());
    }

    /**
     * Method under test: {@link PatientService#getLatestPatients(Integer, Clinic)}
     */
    @Test
    void testGetLatestPatients() {
        when(patientRepository.findAllByClinicOrderByIdDesc(Mockito.<Clinic>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        ArrayList<PatientResponseDTO> patientResponseDTOList = new ArrayList<>();
        when(patientMapper.toPatientResponseDTOList(Mockito.<List<Patient>>any())).thenReturn(patientResponseDTOList);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        List<PatientResponseDTO> actualLatestPatients = patientService.getLatestPatients(3, clinic);
        assertSame(patientResponseDTOList, actualLatestPatients);
        assertTrue(actualLatestPatients.isEmpty());
        verify(patientRepository).findAllByClinicOrderByIdDesc(Mockito.<Clinic>any(), Mockito.<Pageable>any());
        verify(patientMapper).toPatientResponseDTOList(Mockito.<List<Patient>>any());
    }

    /**
     * Method under test: {@link PatientService#getLatestPatients(Integer, Clinic)}
     */
    @Test
    void testGetLatestPatients2() {
        when(patientRepository.findAllByClinicOrderByIdDesc(Mockito.<Clinic>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        when(patientMapper.toPatientResponseDTOList(Mockito.<List<Patient>>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> patientService.getLatestPatients(3, clinic));
        verify(patientRepository).findAllByClinicOrderByIdDesc(Mockito.<Clinic>any(), Mockito.<Pageable>any());
        verify(patientMapper).toPatientResponseDTOList(Mockito.<List<Patient>>any());
    }

    /**
     * Method under test: {@link PatientService#getByClinic(String, String, String, Clinic, int)}
     */
    @Test
    void testGetByClinic() {
        when(patientRepository.findAllByFullNameContainingIgnoreCaseAndClinicOrderByIdAsc(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<Clinic>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        PageImpl<PatientResponseDTO> pageImpl = new PageImpl<>(new ArrayList<>());
        when(patientMapper.toPatientResponseDTOPage(Mockito.<Page<Patient>>any())).thenReturn(pageImpl);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        Page<PatientResponseDTO> actualByClinic = patientService.getByClinic("Jane", "Patronymic", "Doe", clinic, 1);
        assertSame(pageImpl, actualByClinic);
        assertTrue(actualByClinic.toList().isEmpty());
        verify(patientRepository).findAllByFullNameContainingIgnoreCaseAndClinicOrderByIdAsc(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<Clinic>any(), Mockito.<Pageable>any());
        verify(patientMapper).toPatientResponseDTOPage(Mockito.<Page<Patient>>any());
    }

    /**
     * Method under test: {@link PatientService#getByClinic(String, String, String, Clinic, int)}
     */
    @Test
    void testGetByClinic2() {
        when(patientRepository.findAllByFullNameContainingIgnoreCaseAndClinicOrderByIdAsc(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<Clinic>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        when(patientMapper.toPatientResponseDTOPage(Mockito.<Page<Patient>>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> patientService.getByClinic("Jane", "Patronymic", "Doe", clinic, 1));
        verify(patientRepository).findAllByFullNameContainingIgnoreCaseAndClinicOrderByIdAsc(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<Clinic>any(), Mockito.<Pageable>any());
        verify(patientMapper).toPatientResponseDTOPage(Mockito.<Page<Patient>>any());
    }

    /**
     * Method under test: {@link PatientService#getByClinic(String, String, String, Clinic, int)}
     */
    @Test
    void testGetByClinic3() {
        when(patientRepository.findAllByFullNameContainingIgnoreCaseAndClinicOrderByIdAsc(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<Clinic>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        PageImpl<PatientResponseDTO> pageImpl = new PageImpl<>(new ArrayList<>());
        when(patientMapper.toPatientResponseDTOPage(Mockito.<Page<Patient>>any())).thenReturn(pageImpl);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        Page<PatientResponseDTO> actualByClinic = patientService.getByClinic(null, "Patronymic", "Doe", clinic, 1);
        assertSame(pageImpl, actualByClinic);
        assertTrue(actualByClinic.toList().isEmpty());
        verify(patientRepository).findAllByFullNameContainingIgnoreCaseAndClinicOrderByIdAsc(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<Clinic>any(), Mockito.<Pageable>any());
        verify(patientMapper).toPatientResponseDTOPage(Mockito.<Page<Patient>>any());
    }

    /**
     * Method under test: {@link PatientService#getByClinic(String, String, String, Clinic, int)}
     */
    @Test
    void testGetByClinic4() {
        when(patientRepository.findAllByFullNameContainingIgnoreCaseAndClinicOrderByIdAsc(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<Clinic>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        PageImpl<PatientResponseDTO> pageImpl = new PageImpl<>(new ArrayList<>());
        when(patientMapper.toPatientResponseDTOPage(Mockito.<Page<Patient>>any())).thenReturn(pageImpl);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        Page<PatientResponseDTO> actualByClinic = patientService.getByClinic("Jane", null, "Doe", clinic, 1);
        assertSame(pageImpl, actualByClinic);
        assertTrue(actualByClinic.toList().isEmpty());
        verify(patientRepository).findAllByFullNameContainingIgnoreCaseAndClinicOrderByIdAsc(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<Clinic>any(), Mockito.<Pageable>any());
        verify(patientMapper).toPatientResponseDTOPage(Mockito.<Page<Patient>>any());
    }

    /**
     * Method under test: {@link PatientService#getByClinic(String, String, String, Clinic, int)}
     */
    @Test
    void testGetByClinic5() {
        when(patientRepository.findAllByFullNameContainingIgnoreCaseAndClinicOrderByIdAsc(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<Clinic>any(), Mockito.<Pageable>any()))
                .thenReturn(new PageImpl<>(new ArrayList<>()));
        PageImpl<PatientResponseDTO> pageImpl = new PageImpl<>(new ArrayList<>());
        when(patientMapper.toPatientResponseDTOPage(Mockito.<Page<Patient>>any())).thenReturn(pageImpl);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        Page<PatientResponseDTO> actualByClinic = patientService.getByClinic("Jane", "Patronymic", null, clinic, 1);
        assertSame(pageImpl, actualByClinic);
        assertTrue(actualByClinic.toList().isEmpty());
        verify(patientRepository).findAllByFullNameContainingIgnoreCaseAndClinicOrderByIdAsc(Mockito.<String>any(),
                Mockito.<String>any(), Mockito.<String>any(), Mockito.<Clinic>any(), Mockito.<Pageable>any());
        verify(patientMapper).toPatientResponseDTOPage(Mockito.<Page<Patient>>any());
    }

    /**
     * Method under test: {@link PatientService#getById(long)}
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
        Optional<Patient> ofResult = Optional.of(patient2);
        when(patientRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        assertSame(patient2, patientService.getById(1L));
        verify(patientRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PatientService#create(PatientDTO, Clinic)}
     */
    @Test
    void testCreate() {
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

        Patient patient = new Patient();
        patient.setAddress("42 Main St");
        patient.setAppointments(new ArrayList<>());
        patient.setBirthDate(LocalDate.of(1970, 1, 1));
        patient.setClinic(new Clinic());
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender(3);
        patient.setId(1L);
        patient.setLastName("Doe");
        patient.setLogin("Login");
        MedicalCard medicalCard = new MedicalCard();
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

        MedicalCard medicalCard3 = new MedicalCard();
        medicalCard3.setId(1L);
        medicalCard3.setMedicalRecord(new ArrayList<>());
        medicalCard3.setPatient(patient2);

        Patient patient3 = new Patient();
        patient3.setAddress("42 Main St");
        patient3.setAppointments(new ArrayList<>());
        patient3.setBirthDate(LocalDate.of(1970, 1, 1));
        patient3.setClinic(clinic);
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
        when(patientRepository.save(Mockito.<Patient>any())).thenReturn(patient3);

        Clinic clinic3 = new Clinic();
        clinic3.setAddress("42 Main St");
        clinic3.setDoctors(new ArrayList<>());
        clinic3.setId(1L);
        clinic3.setName("Name");
        clinic3.setPatients(new ArrayList<>());
        clinic3.setPhoneNumber("6625550144");

        Clinic clinic4 = new Clinic();
        clinic4.setAddress("42 Main St");
        clinic4.setDoctors(new ArrayList<>());
        clinic4.setId(1L);
        clinic4.setName("Name");
        clinic4.setPatients(new ArrayList<>());
        clinic4.setPhoneNumber("6625550144");

        Patient patient4 = new Patient();
        patient4.setAddress("42 Main St");
        patient4.setAppointments(new ArrayList<>());
        patient4.setBirthDate(LocalDate.of(1970, 1, 1));
        patient4.setClinic(new Clinic());
        patient4.setEmail("jane.doe@example.org");
        patient4.setFirstName("Jane");
        patient4.setGender(3);
        patient4.setId(1L);
        patient4.setLastName("Doe");
        patient4.setLogin("Login");
        patient4.setMedicalCard(new MedicalCard());
        patient4.setPassword("iloveyou");
        patient4.setPatronymic("Patronymic");
        patient4.setPhoneNumber("6625550144");
        patient4.setRole(new HashSet<>());

        MedicalCard medicalCard4 = new MedicalCard();
        medicalCard4.setId(1L);
        medicalCard4.setMedicalRecord(new ArrayList<>());
        medicalCard4.setPatient(patient4);

        Patient patient5 = new Patient();
        patient5.setAddress("42 Main St");
        patient5.setAppointments(new ArrayList<>());
        patient5.setBirthDate(LocalDate.of(1970, 1, 1));
        patient5.setClinic(clinic4);
        patient5.setEmail("jane.doe@example.org");
        patient5.setFirstName("Jane");
        patient5.setGender(3);
        patient5.setId(1L);
        patient5.setLastName("Doe");
        patient5.setLogin("Login");
        patient5.setMedicalCard(medicalCard4);
        patient5.setPassword("iloveyou");
        patient5.setPatronymic("Patronymic");
        patient5.setPhoneNumber("6625550144");
        patient5.setRole(new HashSet<>());

        MedicalCard medicalCard5 = new MedicalCard();
        medicalCard5.setId(1L);
        medicalCard5.setMedicalRecord(new ArrayList<>());
        medicalCard5.setPatient(patient5);

        Patient patient6 = new Patient();
        patient6.setAddress("42 Main St");
        patient6.setAppointments(new ArrayList<>());
        patient6.setBirthDate(LocalDate.of(1970, 1, 1));
        patient6.setClinic(clinic3);
        patient6.setEmail("jane.doe@example.org");
        patient6.setFirstName("Jane");
        patient6.setGender(3);
        patient6.setId(1L);
        patient6.setLastName("Doe");
        patient6.setLogin("Login");
        patient6.setMedicalCard(medicalCard5);
        patient6.setPassword("iloveyou");
        patient6.setPatronymic("Patronymic");
        patient6.setPhoneNumber("6625550144");
        patient6.setRole(new HashSet<>());
        when(patientMapper.toPatient(Mockito.<PatientDTO>any())).thenReturn(patient6);
        when(loginPasswordGenerator.generateLogin()).thenReturn("Generate Login");
        when(loginPasswordGenerator.generatePassword()).thenReturn("iloveyou");

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        Clinic clinic5 = new Clinic();
        clinic5.setAddress("42 Main St");
        clinic5.setDoctors(new ArrayList<>());
        clinic5.setId(1L);
        clinic5.setName("Name");
        clinic5.setPatients(new ArrayList<>());
        clinic5.setPhoneNumber("6625550144");

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAddress("42 Main St");
        patientDTO.setBirthDate(LocalDate.of(1970, 1, 1));
        patientDTO.setClinic(clinic5);
        patientDTO.setEmail("jane.doe@example.org");
        patientDTO.setFirstName("Jane");
        patientDTO.setGender(3);
        patientDTO.setLastName("Doe");
        patientDTO.setPatronymic("Patronymic");
        patientDTO.setPhoneNumber("6625550144");

        Clinic clinic6 = new Clinic();
        clinic6.setAddress("42 Main St");
        clinic6.setDoctors(new ArrayList<>());
        clinic6.setId(1L);
        clinic6.setName("Name");
        clinic6.setPatients(new ArrayList<>());
        clinic6.setPhoneNumber("6625550144");
        PatientWithPassword actualCreateResult = patientService.create(patientDTO, clinic6);
        assertEquals("iloveyou", actualCreateResult.getDecryptedPassword());
        Patient patient7 = actualCreateResult.getPatient();
        assertSame(patient6, patient7);
        assertEquals(1, patient7.getRole().size());
        assertEquals("secret", patient7.getPassword());
        MedicalCard medicalCard6 = patient7.getMedicalCard();
        assertEquals(medicalCard, medicalCard6);
        assertEquals("Generate Login", patient7.getLogin());
        assertEquals(clinic, patient7.getClinic());
        assertSame(patient7, medicalCard6.getPatient());
        verify(patientRepository).save(Mockito.<Patient>any());
        verify(patientMapper).toPatient(Mockito.<PatientDTO>any());
        verify(loginPasswordGenerator).generateLogin();
        verify(loginPasswordGenerator).generatePassword();
        verify(roleRepository).findByName(Mockito.<String>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link PatientService#create(PatientDTO, Clinic)}
     */
    @Test
    void testCreate2() {
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

        Patient patient = new Patient();
        patient.setAddress("42 Main St");
        patient.setAppointments(new ArrayList<>());
        patient.setBirthDate(LocalDate.of(1970, 1, 1));
        patient.setClinic(new Clinic());
        patient.setEmail("jane.doe@example.org");
        patient.setFirstName("Jane");
        patient.setGender(3);
        patient.setId(1L);
        patient.setLastName("Doe");
        patient.setLogin("Login");
        patient.setMedicalCard(new MedicalCard());
        patient.setPassword("iloveyou");
        patient.setPatronymic("Patronymic");
        patient.setPhoneNumber("6625550144");
        patient.setRole(new HashSet<>());

        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setId(1L);
        medicalCard.setMedicalRecord(new ArrayList<>());
        medicalCard.setPatient(patient);

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
        patient2.setMedicalCard(medicalCard);
        patient2.setPassword("iloveyou");
        patient2.setPatronymic("Patronymic");
        patient2.setPhoneNumber("6625550144");
        patient2.setRole(new HashSet<>());

        MedicalCard medicalCard2 = new MedicalCard();
        medicalCard2.setId(1L);
        medicalCard2.setMedicalRecord(new ArrayList<>());
        medicalCard2.setPatient(patient2);

        Patient patient3 = new Patient();
        patient3.setAddress("42 Main St");
        patient3.setAppointments(new ArrayList<>());
        patient3.setBirthDate(LocalDate.of(1970, 1, 1));
        patient3.setClinic(clinic);
        patient3.setEmail("jane.doe@example.org");
        patient3.setFirstName("Jane");
        patient3.setGender(3);
        patient3.setId(1L);
        patient3.setLastName("Doe");
        patient3.setLogin("Login");
        patient3.setMedicalCard(medicalCard2);
        patient3.setPassword("iloveyou");
        patient3.setPatronymic("Patronymic");
        patient3.setPhoneNumber("6625550144");
        patient3.setRole(new HashSet<>());
        when(patientMapper.toPatient(Mockito.<PatientDTO>any())).thenReturn(patient3);
        when(loginPasswordGenerator.generateLogin()).thenReturn("Generate Login");
        when(loginPasswordGenerator.generatePassword()).thenReturn("iloveyou");

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new NotFoundException("An error occurred"));

        Clinic clinic3 = new Clinic();
        clinic3.setAddress("42 Main St");
        clinic3.setDoctors(new ArrayList<>());
        clinic3.setId(1L);
        clinic3.setName("Name");
        clinic3.setPatients(new ArrayList<>());
        clinic3.setPhoneNumber("6625550144");

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAddress("42 Main St");
        patientDTO.setBirthDate(LocalDate.of(1970, 1, 1));
        patientDTO.setClinic(clinic3);
        patientDTO.setEmail("jane.doe@example.org");
        patientDTO.setFirstName("Jane");
        patientDTO.setGender(3);
        patientDTO.setLastName("Doe");
        patientDTO.setPatronymic("Patronymic");
        patientDTO.setPhoneNumber("6625550144");

        Clinic clinic4 = new Clinic();
        clinic4.setAddress("42 Main St");
        clinic4.setDoctors(new ArrayList<>());
        clinic4.setId(1L);
        clinic4.setName("Name");
        clinic4.setPatients(new ArrayList<>());
        clinic4.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> patientService.create(patientDTO, clinic4));
        verify(patientMapper).toPatient(Mockito.<PatientDTO>any());
        verify(loginPasswordGenerator).generateLogin();
        verify(loginPasswordGenerator).generatePassword();
        verify(roleRepository).findByName(Mockito.<String>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link PatientService#update(Long, PatientDTO)}
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
        Optional<Patient> ofResult = Optional.of(patient2);

        Clinic clinic3 = new Clinic();
        clinic3.setAddress("42 Main St");
        clinic3.setDoctors(new ArrayList<>());
        clinic3.setId(1L);
        clinic3.setName("Name");
        clinic3.setPatients(new ArrayList<>());
        clinic3.setPhoneNumber("6625550144");

        Clinic clinic4 = new Clinic();
        clinic4.setAddress("42 Main St");
        clinic4.setDoctors(new ArrayList<>());
        clinic4.setId(1L);
        clinic4.setName("Name");
        clinic4.setPatients(new ArrayList<>());
        clinic4.setPhoneNumber("6625550144");

        Patient patient3 = new Patient();
        patient3.setAddress("42 Main St");
        patient3.setAppointments(new ArrayList<>());
        patient3.setBirthDate(LocalDate.of(1970, 1, 1));
        patient3.setClinic(new Clinic());
        patient3.setEmail("jane.doe@example.org");
        patient3.setFirstName("Jane");
        patient3.setGender(3);
        patient3.setId(1L);
        patient3.setLastName("Doe");
        patient3.setLogin("Login");
        patient3.setMedicalCard(new MedicalCard());
        patient3.setPassword("iloveyou");
        patient3.setPatronymic("Patronymic");
        patient3.setPhoneNumber("6625550144");
        patient3.setRole(new HashSet<>());

        MedicalCard medicalCard3 = new MedicalCard();
        medicalCard3.setId(1L);
        medicalCard3.setMedicalRecord(new ArrayList<>());
        medicalCard3.setPatient(patient3);

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
        patient4.setMedicalCard(medicalCard3);
        patient4.setPassword("iloveyou");
        patient4.setPatronymic("Patronymic");
        patient4.setPhoneNumber("6625550144");
        patient4.setRole(new HashSet<>());

        MedicalCard medicalCard4 = new MedicalCard();
        medicalCard4.setId(1L);
        medicalCard4.setMedicalRecord(new ArrayList<>());
        medicalCard4.setPatient(patient4);

        Patient patient5 = new Patient();
        patient5.setAddress("42 Main St");
        patient5.setAppointments(new ArrayList<>());
        patient5.setBirthDate(LocalDate.of(1970, 1, 1));
        patient5.setClinic(clinic3);
        patient5.setEmail("jane.doe@example.org");
        patient5.setFirstName("Jane");
        patient5.setGender(3);
        patient5.setId(1L);
        patient5.setLastName("Doe");
        patient5.setLogin("Login");
        patient5.setMedicalCard(medicalCard4);
        patient5.setPassword("iloveyou");
        patient5.setPatronymic("Patronymic");
        patient5.setPhoneNumber("6625550144");
        patient5.setRole(new HashSet<>());
        when(patientRepository.save(Mockito.<Patient>any())).thenReturn(patient5);
        when(patientRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Clinic clinic5 = new Clinic();
        clinic5.setAddress("42 Main St");
        clinic5.setDoctors(new ArrayList<>());
        clinic5.setId(1L);
        clinic5.setName("Name");
        clinic5.setPatients(new ArrayList<>());
        clinic5.setPhoneNumber("6625550144");

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAddress("42 Main St");
        patientDTO.setBirthDate(LocalDate.of(1970, 1, 1));
        patientDTO.setClinic(clinic5);
        patientDTO.setEmail("jane.doe@example.org");
        patientDTO.setFirstName("Jane");
        patientDTO.setGender(3);
        patientDTO.setLastName("Doe");
        patientDTO.setPatronymic("Patronymic");
        patientDTO.setPhoneNumber("6625550144");
        assertSame(patient5, patientService.update(1L, patientDTO));
        verify(patientRepository).save(Mockito.<Patient>any());
        verify(patientRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PatientService#update(Long, PatientDTO)}
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
        Optional<Patient> ofResult = Optional.of(patient2);
        when(patientRepository.save(Mockito.<Patient>any())).thenThrow(new NotFoundException("An error occurred"));
        when(patientRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        Clinic clinic3 = new Clinic();
        clinic3.setAddress("42 Main St");
        clinic3.setDoctors(new ArrayList<>());
        clinic3.setId(1L);
        clinic3.setName("Name");
        clinic3.setPatients(new ArrayList<>());
        clinic3.setPhoneNumber("6625550144");

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setAddress("42 Main St");
        patientDTO.setBirthDate(LocalDate.of(1970, 1, 1));
        patientDTO.setClinic(clinic3);
        patientDTO.setEmail("jane.doe@example.org");
        patientDTO.setFirstName("Jane");
        patientDTO.setGender(3);
        patientDTO.setLastName("Doe");
        patientDTO.setPatronymic("Patronymic");
        patientDTO.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> patientService.update(1L, patientDTO));
        verify(patientRepository).save(Mockito.<Patient>any());
        verify(patientRepository).findById(Mockito.<Long>any());
    }

    /**
     * Method under test: {@link PatientService#getPatientsStatisticsByGender(Clinic)}
     */
    @Test
    void testGetPatientsStatisticsByGender() {
        when(patientRepository.getPatientsCountByGenderAndClinic(anyInt(), Mockito.<Clinic>any())).thenReturn(3);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        PatientStatisticsByGenderDTO actualPatientsStatisticsByGender = patientService
                .getPatientsStatisticsByGender(clinic);
        assertEquals(3, actualPatientsStatisticsByGender.getFemale());
        assertEquals(3, actualPatientsStatisticsByGender.getMale());
        verify(patientRepository, atLeast(1)).getPatientsCountByGenderAndClinic(anyInt(), Mockito.<Clinic>any());
    }

    /**
     * Method under test: {@link PatientService#getPatientsStatisticsByGender(Clinic)}
     */
    @Test
    void testGetPatientsStatisticsByGender2() {
        when(patientRepository.getPatientsCountByGenderAndClinic(anyInt(), Mockito.<Clinic>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> patientService.getPatientsStatisticsByGender(clinic));
        verify(patientRepository).getPatientsCountByGenderAndClinic(anyInt(), Mockito.<Clinic>any());
    }

    /**
     * Method under test: {@link PatientService#getPatientsStatisticsByAge(Clinic)}
     */
    @Test
    void testGetPatientsStatisticsByAge() {
        when(patientRepository.countPatientsByAgeRangeAndClinic(anyInt(), anyInt(), Mockito.<Clinic>any())).thenReturn(3);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        PatientStatisticsByAgeDTO actualPatientsStatisticsByAge = patientService.getPatientsStatisticsByAge(clinic);
        assertEquals(3, actualPatientsStatisticsByAge.getAdult());
        assertEquals(3, actualPatientsStatisticsByAge.getElderly());
        assertEquals(3, actualPatientsStatisticsByAge.getChild());
        verify(patientRepository, atLeast(1)).countPatientsByAgeRangeAndClinic(anyInt(), anyInt(), Mockito.<Clinic>any());
    }

    /**
     * Method under test: {@link PatientService#getPatientsStatisticsByAge(Clinic)}
     */
    @Test
    void testGetPatientsStatisticsByAge2() {
        when(patientRepository.countPatientsByAgeRangeAndClinic(anyInt(), anyInt(), Mockito.<Clinic>any()))
                .thenThrow(new NotFoundException("An error occurred"));

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> patientService.getPatientsStatisticsByAge(clinic));
        verify(patientRepository).countPatientsByAgeRangeAndClinic(anyInt(), anyInt(), Mockito.<Clinic>any());
    }
}

