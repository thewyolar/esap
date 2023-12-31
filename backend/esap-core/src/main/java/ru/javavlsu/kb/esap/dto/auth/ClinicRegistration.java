package ru.javavlsu.kb.esap.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRegistration {

    private String name;

    private String address;

    private String phoneNumber;
}
