package com.jeremyantoine.speedjumper.Jeu;

import java.io.IOException;

class InvalidFormatException extends IOException {

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