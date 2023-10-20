package ru.javavlsu.kb.esap.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.javavlsu.kb.esap.exception.NotFoundException;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.model.Doctor;
import ru.javavlsu.kb.esap.model.MedicalCard;
import ru.javavlsu.kb.esap.model.MedicalRecord;
import ru.javavlsu.kb.esap.model.Patient;
import ru.javavlsu.kb.esap.repository.MedicalCardRepository;
import ru.javavlsu.kb.esap.repository.MedicalRecordRepository;

@ContextConfiguration(classes = {MedicalCardService.class})
@ExtendWith(SpringExtension.class)
class MedicalCardServiceDiffblueTest {
    @MockBean
    private MedicalCardRepository medicalCardRepository;

    @Autowired
    private MedicalCardService medicalCardService;

    @MockBean
    private MedicalRecordRepository medicalRecordRepository;

    /**
     * Method under test: {@link MedicalCardService#getMedicalCardByPatient(Patient)}
     */
    @Test
    void testGetMedicalCardByPatient() throws NotFoundException {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

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
        patient2.setClinic(clinic);
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
        Optional<MedicalCard> ofResult = Optional.of(medicalCard2);
        when(medicalCardRepository.findByPatientOrderByMedicalRecordDateDesc(Mockito.<Patient>any()))
                .thenReturn(ofResult);

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

        MedicalCard medicalCard3 = new MedicalCard();
        medicalCard3.setId(1L);
        medicalCard3.setMedicalRecord(new ArrayList<>());
        medicalCard3.setPatient(new Patient());

        Patient patient3 = new Patient();
        patient3.setAddress("42 Main St");
        patient3.setAppointments(new ArrayList<>());
        patient3.setBirthDate(LocalDate.of(1970, 1, 1));
        patient3.setClinic(clinic3);
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
        patient4.setClinic(clinic2);
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
        assertSame(medicalCard2, medicalCardService.getMedicalCardByPatient(patient4));
        verify(medicalCardRepository).findByPatientOrderByMedicalRecordDateDesc(Mockito.<Patient>any());
    }

    /**
     * Method under test: {@link MedicalCardService#getMedicalCardByPatient(Patient)}
     */
    @Test
    void testGetMedicalCardByPatient2() throws NotFoundException {
        Optional<MedicalCard> emptyResult = Optional.empty();
        when(medicalCardRepository.findByPatientOrderByMedicalRecordDateDesc(Mockito.<Patient>any()))
                .thenReturn(emptyResult);

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
        assertThrows(NotFoundException.class, () -> medicalCardService.getMedicalCardByPatient(patient2));
        verify(medicalCardRepository).findByPatientOrderByMedicalRecordDateDesc(Mockito.<Patient>any());
    }

    /**
     * Method under test: {@link MedicalCardService#getMedicalRecordByMedicalCard(MedicalCard)}
     */
    @Test
    void testGetMedicalRecordByMedicalCard() throws NotFoundException {
        ArrayList<MedicalRecord> medicalRecordList = new ArrayList<>();
        when(medicalRecordRepository.findByMedicalCardOrderByDateDesc(Mockito.<MedicalCard>any()))
                .thenReturn(medicalRecordList);

        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

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
        patient2.setClinic(clinic);
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
        List<MedicalRecord> actualMedicalRecordByMedicalCard = medicalCardService
                .getMedicalRecordByMedicalCard(medicalCard2);
        assertSame(medicalRecordList, actualMedicalRecordByMedicalCard);
        assertTrue(actualMedicalRecordByMedicalCard.isEmpty());
        verify(medicalRecordRepository).findByMedicalCardOrderByDateDesc(Mockito.<MedicalCard>any());
    }

    /**
     * Method under test: {@link MedicalCardService#getMedicalCardByPatientAndMedicalRecordSpecializationDoctor(Patient, String)}
     */
    @Test
    void testGetMedicalCardByPatientAndMedicalRecordSpecializationDoctor() throws NotFoundException {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

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
        patient2.setClinic(clinic);
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
        Optional<MedicalCard> ofResult = Optional.of(medicalCard2);
        when(medicalCardRepository.findMedicalCardByPatientAndMedicalRecordSpecializationDoctor(Mockito.<Patient>any(),
                Mockito.<String>any())).thenReturn(ofResult);

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

        MedicalCard medicalCard3 = new MedicalCard();
        medicalCard3.setId(1L);
        medicalCard3.setMedicalRecord(new ArrayList<>());
        medicalCard3.setPatient(new Patient());

        Patient patient3 = new Patient();
        patient3.setAddress("42 Main St");
        patient3.setAppointments(new ArrayList<>());
        patient3.setBirthDate(LocalDate.of(1970, 1, 1));
        patient3.setClinic(clinic3);
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
        patient4.setClinic(clinic2);
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
        assertSame(medicalCard2, medicalCardService.getMedicalCardByPatientAndMedicalRecordSpecializationDoctor(patient4,
                "Specialization Doctor"));
        verify(medicalCardRepository).findMedicalCardByPatientAndMedicalRecordSpecializationDoctor(Mockito.<Patient>any(),
                Mockito.<String>any());
    }

    /**
     * Method under test: {@link MedicalCardService#createMedicalRecord(MedicalRecord, MedicalCard, Doctor)}
     */
    @Test
    void testCreateMedicalRecord() {
        Clinic clinic = new Clinic();
        clinic.setAddress("42 Main St");
        clinic.setDoctors(new ArrayList<>());
        clinic.setId(1L);
        clinic.setName("Name");
        clinic.setPatients(new ArrayList<>());
        clinic.setPhoneNumber("6625550144");

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
        patient2.setClinic(clinic);
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

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setAnalyzes(new ArrayList<>());
        medicalRecord.setDate(LocalDate.of(1970, 1, 1));
        medicalRecord.setFioAndSpecializationDoctor("Fio And Specialization Doctor");
        medicalRecord.setId(1L);
        medicalRecord.setMedicalCard(medicalCard2);
        medicalRecord.setRecord("Record");
        when(medicalRecordRepository.save(Mockito.<MedicalRecord>any())).thenReturn(medicalRecord);

        Clinic clinic2 = new Clinic();
        clinic2.setAddress("42 Main St");
        clinic2.setDoctors(new ArrayList<>());
        clinic2.setId(1L);
        clinic2.setName("Name");
        clinic2.setPatients(new ArrayList<>());
        clinic2.setPhoneNumber("6625550144");

        MedicalCard medicalCard3 = new MedicalCard();
        medicalCard3.setId(1L);
        medicalCard3.setMedicalRecord(new ArrayList<>());
        medicalCard3.setPatient(new Patient());

        Patient patient3 = new Patient();
        patient3.setAddress("42 Main St");
        patient3.setAppointments(new ArrayList<>());
        patient3.setBirthDate(LocalDate.of(1970, 1, 1));
        patient3.setClinic(clinic2);
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

        MedicalRecord medicalRecord2 = new MedicalRecord();
        medicalRecord2.setAnalyzes(new ArrayList<>());
        medicalRecord2.setDate(LocalDate.of(1970, 1, 1));
        medicalRecord2.setFioAndSpecializationDoctor("Fio And Specialization Doctor");
        medicalRecord2.setId(1L);
        medicalRecord2.setMedicalCard(medicalCard4);
        medicalRecord2.setRecord("Record");

        Clinic clinic3 = new Clinic();
        clinic3.setAddress("42 Main St");
        clinic3.setDoctors(new ArrayList<>());
        clinic3.setId(1L);
        clinic3.setName("Name");
        clinic3.setPatients(new ArrayList<>());
        clinic3.setPhoneNumber("6625550144");

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

        MedicalCard medicalCard5 = new MedicalCard();
        medicalCard5.setId(1L);
        medicalCard5.setMedicalRecord(new ArrayList<>());
        medicalCard5.setPatient(patient4);

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
        patient5.setMedicalCard(medicalCard5);
        patient5.setPassword("iloveyou");
        patient5.setPatronymic("Patronymic");
        patient5.setPhoneNumber("6625550144");
        patient5.setRole(new HashSet<>());

        MedicalCard medicalCard6 = new MedicalCard();
        medicalCard6.setId(1L);
        medicalCard6.setMedicalRecord(new ArrayList<>());
        medicalCard6.setPatient(patient5);

        Clinic clinic4 = new Clinic();
        clinic4.setAddress("42 Main St");
        clinic4.setDoctors(new ArrayList<>());
        clinic4.setId(1L);
        clinic4.setName("Name");
        clinic4.setPatients(new ArrayList<>());
        clinic4.setPhoneNumber("6625550144");

        Doctor doctor = new Doctor();
        doctor.setClinic(clinic4);
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
        medicalCardService.createMedicalRecord(medicalRecord2, medicalCard6, doctor);
        verify(medicalRecordRepository).save(Mockito.<MedicalRecord>any());
        assertEquals(medicalCard2, medicalRecord2.getMedicalCard());
        assertEquals("Specialization: Doe Jane Patronymic", medicalRecord2.getFioAndSpecializationDoctor());
    }
}

