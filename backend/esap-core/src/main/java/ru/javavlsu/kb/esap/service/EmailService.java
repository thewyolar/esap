package ru.javavlsu.kb.esap.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.javavlsu.kb.esap.dto.EmailRequest;
import ru.javavlsu.kb.esap.exception.EmailSendingException;
import ru.javavlsu.kb.esap.model.Patient;

@Service
public class EmailService {
    public final RestTemplate restTemplate;
    private final String emailServiceUrl = "http://localhost:8082/email/send";

    public EmailService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendUserData(Patient patient) {
        String emailSubject = "Добро пожаловать в нашу клинику";
        String emailMessage = "Ваш логин: " + patient.getLogin() +
                "\nВаш пароль: " + patient.getPassword();
        EmailRequest emailRequest = new EmailRequest(patient.getEmail(), emailSubject, emailMessage);

        try {
            restTemplate.postForEntity(emailServiceUrl, emailRequest, String.class);
        } catch (EmailSendingException mailException) {
            throw new EmailSendingException("Unable to send email");
        }
    }
}
