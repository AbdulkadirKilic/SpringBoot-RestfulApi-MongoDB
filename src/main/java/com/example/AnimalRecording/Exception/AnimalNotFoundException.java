package com.example.AnimalRecording.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AnimalNotFoundException extends RuntimeException {
    private String  message2;

    public AnimalNotFoundException(String message, String message2) {
        super(message);
        this.message2=message2;
    }

    public String getMessage2() {
        return message2;
    }
}
