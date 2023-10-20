package ru.javavlsu.kb.esap.service;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.javavlsu.kb.esap.dto.auth.AuthenticationDTO;
import ru.javavlsu.kb.esap.dto.auth.DoctorRegistration;
import ru.javavlsu.kb.esap.exception.NotFoundException;
import ru.javavlsu.kb.esap.mapper.DoctorMapper;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.Role;
import ru.javavlsu.kb.esap.model.User;
import ru.javavlsu.kb.esap.repository.ClinicRepository;
import ru.javavlsu.kb.esap.repository.DoctorRepository;
import ru.javavlsu.kb.esap.repository.RoleRepository;
import ru.javavlsu.kb.esap.repository.UserRepository;
import ru.javavlsu.kb.esap.util.LoginPasswordGenerator;

@ContextConfiguration(classes = {RegistrationService.class})
@ExtendWith(SpringExtension.class)
class RegistrationServiceDiffblueTest {
    @MockBean
    private ClinicRepository clinicRepository;

    @MockBean
    private DoctorMapper doctorMapper;

    @MockBean
    private DoctorRepository doctorRepository;

    @MockBean
    private LoginPasswordGenerator loginPasswordGenerator;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RegistrationService registrationService;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link RegistrationService#registrationClinic(Clinic, Doctor)}
     */
    @Test
    void testRegistrationClinic() throws NotFoundException {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");
        when(clinicRepository.save(Mockito.<Clinic>any())).thenReturn(clinic);

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);
        when(loginPasswordGenerator.generateLogin()).thenReturn("Generate Login");
        when(loginPasswordGenerator.generatePassword()).thenReturn("iloveyou");
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

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
        assertArrayEquals(new String[]{"Generate Login", "iloveyou"},
                registrationService.registrationClinic(clinic2, doctor));
        verify(clinicRepository).save(Mockito.<Clinic>any());
        verify(roleRepository).findByName(Mockito.<String>any());
        verify(loginPasswordGenerator).generateLogin();
        verify(loginPasswordGenerator).generatePassword();
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
        assertEquals(1, clinic2.getDoctors().size());
        assertSame(clinic2, doctor.getClinic());
        assertEquals(1, doctor.getRole().size());
        assertEquals("secret", doctor.getPassword());
        assertEquals("Generate Login", doctor.getLogin());
    }

    /**
     * Method under test: {@link RegistrationService#registrationClinic(Clinic, Doctor)}
     */
    @Test
    void testRegistrationClinic2() throws NotFoundException {
        when(loginPasswordGenerator.generateLogin()).thenReturn("Generate Login");
        when(loginPasswordGenerator.generatePassword()).thenReturn("iloveyou");
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new NotFoundException("An error occurred"));

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

        Doctor doctor = new Doctor();
        doctor.setClinic(clinic2);
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
        assertThrows(NotFoundException.class, () -> registrationService.registrationClinic(clinic, doctor));
        verify(loginPasswordGenerator).generateLogin();
        verify(loginPasswordGenerator).generatePassword();
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link RegistrationService#registrationClinic(Clinic, Doctor)}
     */
    @Test
    void testRegistrationClinic3() throws NotFoundException {
        Optional<Role> emptyResult = Optional.empty();
        when(roleRepository.findByName(Mockito.<String>any())).thenReturn(emptyResult);
        when(loginPasswordGenerator.generateLogin()).thenReturn("Generate Login");
        when(loginPasswordGenerator.generatePassword()).thenReturn("iloveyou");
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

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

        Doctor doctor = new Doctor();
        doctor.setClinic(clinic2);
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
        assertThrows(NotFoundException.class, () -> registrationService.registrationClinic(clinic, doctor));
        verify(roleRepository).findByName(Mockito.<String>any());
        verify(loginPasswordGenerator).generateLogin();
        verify(loginPasswordGenerator).generatePassword();
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link RegistrationService#registrationDoctor(DoctorRegistration, Clinic)}
     */
    @Test
    void testRegistrationDoctor() throws NotFoundException {
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
        when(doctorRepository.save(Mockito.<Doctor>any())).thenReturn(doctor);

        Role role = new Role();
        role.setId(1L);
        role.setName("Name");
        Optional<Role> ofResult = Optional.of(role);
        when(roleRepository.findByName(Mockito.<String>any())).thenReturn(ofResult);

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
        when(doctorMapper.toDoctor(Mockito.<DoctorRegistration>any())).thenReturn(doctor2);
        when(loginPasswordGenerator.generateLogin()).thenReturn("Generate Login");
        when(loginPasswordGenerator.generatePassword()).thenReturn("iloveyou");
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        DoctorRegistration doctorDTO = new DoctorRegistration("Jane", "Patronymic", "Doe", "Specialization", 3);

        Clinic clinic3 = new Clinic();
        clinic3.setAddress("42 Main St");
        clinic3.setDoctors(new ArrayList<>());
        clinic3.setId(1L);
        clinic3.setName("Name");
        clinic3.setPatients(new ArrayList<>());
        clinic3.setPhoneNumber("6625550144");
        assertArrayEquals(new String[]{"Generate Login", "iloveyou"},
                registrationService.registrationDoctor(doctorDTO, clinic3));
        verify(doctorRepository).save(Mockito.<Doctor>any());
        verify(roleRepository).findByName(Mockito.<String>any());
        verify(doctorMapper).toDoctor(Mockito.<DoctorRegistration>any());
        verify(loginPasswordGenerator).generateLogin();
        verify(loginPasswordGenerator).generatePassword();
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link RegistrationService#registrationDoctor(DoctorRegistration, Clinic)}
     */
    @Test
    void testRegistrationDoctor2() throws NotFoundException {
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
        when(doctorMapper.toDoctor(Mockito.<DoctorRegistration>any())).thenReturn(doctor);
        when(loginPasswordGenerator.generateLogin()).thenReturn("Generate Login");
        when(loginPasswordGenerator.generatePassword()).thenReturn("iloveyou");
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new NotFoundException("An error occurred"));
        DoctorRegistration doctorDTO = new DoctorRegistration("Jane", "Patronymic", "Doe", "Specialization", 3);

        Clinic clinic2 = new Clinic();
        clinic2.setAddress("42 Main St");
        clinic2.setDoctors(new ArrayList<>());
        clinic2.setId(1L);
        clinic2.setName("Name");
        clinic2.setPatients(new ArrayList<>());
        clinic2.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> registrationService.registrationDoctor(doctorDTO, clinic2));
        verify(doctorMapper).toDoctor(Mockito.<DoctorRegistration>any());
        verify(loginPasswordGenerator).generateLogin();
        verify(loginPasswordGenerator).generatePassword();
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link RegistrationService#registrationDoctor(DoctorRegistration, Clinic)}
     */
    @Test
    void testRegistrationDoctor3() throws NotFoundException {
        Optional<Role> emptyResult = Optional.empty();
        when(roleRepository.findByName(Mockito.<String>any())).thenReturn(emptyResult);

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
        when(doctorMapper.toDoctor(Mockito.<DoctorRegistration>any())).thenReturn(doctor);
        when(loginPasswordGenerator.generateLogin()).thenReturn("Generate Login");
        when(loginPasswordGenerator.generatePassword()).thenReturn("iloveyou");
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");
        DoctorRegistration doctorDTO = new DoctorRegistration("Jane", "Patronymic", "Doe", "Specialization", 3);

        Clinic clinic2 = new Clinic();
        clinic2.setAddress("42 Main St");
        clinic2.setDoctors(new ArrayList<>());
        clinic2.setId(1L);
        clinic2.setName("Name");
        clinic2.setPatients(new ArrayList<>());
        clinic2.setPhoneNumber("6625550144");
        assertThrows(NotFoundException.class, () -> registrationService.registrationDoctor(doctorDTO, clinic2));
        verify(roleRepository).findByName(Mockito.<String>any());
        verify(doctorMapper).toDoctor(Mockito.<DoctorRegistration>any());
        verify(loginPasswordGenerator).generateLogin();
        verify(loginPasswordGenerator).generatePassword();
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link RegistrationService#passwordReset(AuthenticationDTO)}
     */
    @Test
    void testPasswordReset() {
        User user = new User();
        user.setId(1L);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        user.setRole(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);

        User user2 = new User();
        user2.setId(1L);
        user2.setLogin("Login");
        user2.setPassword("iloveyou");
        user2.setRole(new HashSet<>());
        when(userRepository.save(Mockito.<User>any())).thenReturn(user2);
        when(userRepository.findByLogin(Mockito.<String>any())).thenReturn(ofResult);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenReturn("secret");

        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setLogin("Login");
        authenticationDTO.setPassword("iloveyou");
        registrationService.passwordReset(authenticationDTO);
        verify(userRepository).save(Mockito.<User>any());
        verify(userRepository).findByLogin(Mockito.<String>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link RegistrationService#passwordReset(AuthenticationDTO)}
     */
    @Test
    void testPasswordReset2() {
        User user = new User();
        user.setId(1L);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        user.setRole(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByLogin(Mockito.<String>any())).thenReturn(ofResult);
        when(passwordEncoder.encode(Mockito.<CharSequence>any())).thenThrow(new NotFoundException("An error occurred"));

        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setLogin("Login");
        authenticationDTO.setPassword("iloveyou");
        assertThrows(NotFoundException.class, () -> registrationService.passwordReset(authenticationDTO));
        verify(userRepository).findByLogin(Mockito.<String>any());
        verify(passwordEncoder).encode(Mockito.<CharSequence>any());
    }

    /**
     * Method under test: {@link RegistrationService#passwordReset(AuthenticationDTO)}
     */
    @Test
    void testPasswordReset3() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findByLogin(Mockito.<String>any())).thenReturn(emptyResult);

        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setLogin("Login");
        authenticationDTO.setPassword("iloveyou");
        assertThrows(NotFoundException.class, () -> registrationService.passwordReset(authenticationDTO));
        verify(userRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link RegistrationService#getAllRoles()}
     */
    @Test
    void testGetAllRoles() {
        when(roleRepository.findAll()).thenReturn(new ArrayList<>());
        assertTrue(registrationService.getAllRoles().isEmpty());
        verify(roleRepository).findAll();
    }

    /**
     * Method under test: {@link RegistrationService#getAllRoles()}
     */
    @Test
    void testGetAllRoles2() {
        Role role = new Role();
        role.setId(1L);
        role.setName("Name");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        when(roleRepository.findAll()).thenReturn(roleList);
        List<String> actualAllRoles = registrationService.getAllRoles();
        assertEquals(1, actualAllRoles.size());
        assertEquals("Name", actualAllRoles.get(0));
        verify(roleRepository).findAll();
    }

    /**
     * Method under test: {@link RegistrationService#getAllRoles()}
     */
    @Test
    void testGetAllRoles3() {
        Role role = new Role();
        role.setId(1L);
        role.setName("Name");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("Name");

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role2);
        roleList.add(role);
        when(roleRepository.findAll()).thenReturn(roleList);
        List<String> actualAllRoles = registrationService.getAllRoles();
        assertEquals(2, actualAllRoles.size());
        assertEquals("Name", actualAllRoles.get(0));
        assertEquals("Name", actualAllRoles.get(1));
        verify(roleRepository).findAll();
    }

    /**
     * Method under test: {@link RegistrationService#getAllRoles()}
     */
    @Test
    void testGetAllRoles4() {
        when(roleRepository.findAll()).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> registrationService.getAllRoles());
        verify(roleRepository).findAll();
    }
}

