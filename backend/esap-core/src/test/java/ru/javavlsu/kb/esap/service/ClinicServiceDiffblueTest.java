package ru.javavlsu.kb.esap.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.javavlsu.kb.esap.model.Clinic;
import ru.javavlsu.kb.esap.repository.ClinicRepository;

@ContextConfiguration(classes = {ClinicService.class})
@ExtendWith(SpringExtension.class)
class ClinicServiceDiffblueTest {
    @MockBean
    private ClinicRepository clinicRepository;

    @Autowired
    private ClinicService clinicService;

    @MockBean
    private DoctorService doctorService;

    /**
     * Method under test: {@link ClinicService#getAll()}
     */
    @Test
    void testGetAll() {
        ArrayList<Clinic> clinicList = new ArrayList<>();
        when(clinicRepository.findAll()).thenReturn(clinicList);
        List<Clinic> actualAll = clinicService.getAll();
        assertSame(clinicList, actualAll);
        assertTrue(actualAll.isEmpty());
        verify(clinicRepository).findAll();
    }
}

