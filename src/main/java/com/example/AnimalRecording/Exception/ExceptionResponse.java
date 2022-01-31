package com.example.AnimalRecording.Exception;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private String message;
    private String message2;
    private String details;



    public ExceptionResponse(Date timestamp, String message, String details, String malca) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.message2 =malca;

    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public String getMessage2(){
        return message2;
    }



}
