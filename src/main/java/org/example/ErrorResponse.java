package org.example;

import java.time.LocalDateTime;

public class ErrorResponse {

    public String message;
    public StackTraceElement[] stactTrace;
    public LocalDateTime timestamp;

    public ErrorResponse(String message, StackTraceElement[] stactTrace, LocalDateTime timestamp) {
        this.message = message;
        this.stactTrace = stactTrace;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StackTraceElement[] getStactTrace() {
        return stactTrace;
    }

    public void setStactTrace(StackTraceElement[] stactTrace) {
        this.stactTrace = stactTrace;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

