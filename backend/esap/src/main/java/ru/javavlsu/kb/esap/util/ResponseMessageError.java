package ru.javavlsu.kb.esap.util;

import lombok.Getter;
import lombok.Setter;

public class ResponseMessageError {

    @Getter
    @Setter
    private String message;

    public ResponseMessageError(String message) {
        this.message = message;
    }

}
