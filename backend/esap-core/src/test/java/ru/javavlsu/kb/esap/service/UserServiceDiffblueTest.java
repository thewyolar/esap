package ru.javavlsu.kb.esap.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

@ContextConfiguration(classes = {UserService.class})
@ExtendWith(SpringExtension.class)
class UserServiceDiffblueTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * Method under test: {@link UserService#userExists(String)}
     */
    @Test
    void testUserExists() {
        User user = new User();
        user.setId(1L);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        user.setRole(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByLogin(Mockito.<String>any())).thenReturn(ofResult);
        assertTrue(userService.userExists("Login"));
        verify(userRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#userExists(String)}
     */
    @Test
    void testUserExists2() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findByLogin(Mockito.<String>any())).thenReturn(emptyResult);
        assertFalse(userService.userExists("Login"));
        verify(userRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#userExists(String)}
     */
    @Test
    void testUserExists3() {
        when(userRepository.findByLogin(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.userExists("Login"));
        verify(userRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getRoles(String)}
     */
    @Test
    void testGetRoles() {
        User user = new User();
        user.setId(1L);
        user.setLogin("Login");
        user.setPassword("iloveyou");
        user.setRole(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);
        when(userRepository.findByLogin(Mockito.<String>any())).thenReturn(ofResult);
        assertTrue(userService.getRoles("Login").isEmpty());
        verify(userRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getRoles(String)}
     */
    @Test
    void testGetRoles2() {
        Optional<User> emptyResult = Optional.empty();
        when(userRepository.findByLogin(Mockito.<String>any())).thenReturn(emptyResult);
        assertThrows(NotFoundException.class, () -> userService.getRoles("Login"));
        verify(userRepository).findByLogin(Mockito.<String>any());
    }

    /**
     * Method under test: {@link UserService#getRoles(String)}
     */
    @Test
    void testGetRoles3() {
        when(userRepository.findByLogin(Mockito.<String>any())).thenThrow(new NotFoundException("An error occurred"));
        assertThrows(NotFoundException.class, () -> userService.getRoles("Login"));
        verify(userRepository).findByLogin(Mockito.<String>any());
    }
}

