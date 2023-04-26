package ru.javavlsu.kb.esap.util;

import ru.javavlsu.kb.esap.model.Doctor;

import java.security.SecureRandom;
import java.util.Random;

public class LoginPasswordGenerator {

    private static final int PASSWORD_LENGTH = 8;
    private static final String PASSWORD_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890!@#$%^&*()_-+={}[]:;\"'<>,.?/|\\";

    public static String generateLogin() {
        String login = "";
        String characters = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(characters.length());
            login += characters.charAt(index);
        }
        login += String.format("%04d", new Random().nextInt(10000)); // Генерируем случайный 4-х значный номер
        return login.toLowerCase();
    }

    public static String generatePassword() {
        Random random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(PASSWORD_CHARS.length());
            password.append(PASSWORD_CHARS.charAt(index));
        }
        return password.toString();
    }

}
