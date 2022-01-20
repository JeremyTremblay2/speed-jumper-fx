package com.jeremyantoine.speedjumper.utilitaire;

import java.io.IOException;

public class InvalidFormatException extends IOException {

    public InvalidFormatException() {}

    public InvalidFormatException(String message) {
        super(message);
    }

    public InvalidFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFormatException(Throwable cause) {
        super(cause);
    }
}