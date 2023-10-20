package ru.javavlsu.kb.esap.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.javavlsu.kb.esap.exception.NotFoundException;
import ru.javavlsu.kb.esap.model.User;
import ru.javavlsu.kb.esap.repository.UserRepository;

@ContextConfiguration(classes = {UserDetailsService.class})
@ExtendWith(SpringExtension.class)
class UserDetailsServiceDiffblueTest {
    @Autowired
    private UserDetailsService userDetailsService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws NotFoundException {
        User user = new User();
        user.setId(1L);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        user.setRole(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByLogin(Mockito.<String>any())).thenReturn(ofResult);
        assertEquals("Login", userDetailsService.loadUserByUsername("janedoe").getUsername());
        verify(userRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws NotFoundException {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findByLogin(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(NotFoundException.class, () -> userDetailsService.loadUserByUsername("janedoe"));
        verify(userRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserDetailsService#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername3() throws NotFoundException {
        when(userRepository.findByLogin(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userDetailsService.loadUserByUsername("janedoe"));
        verify(userRepository).findByLogin(Mockito.<String>any());
    }
}

