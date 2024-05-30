package com.exam.ota.notes.exception;

public class NoteRequiredFieldException extends RuntimeException {
    public NoteRequiredFieldException(String message) {
        super(message);
    }
}
