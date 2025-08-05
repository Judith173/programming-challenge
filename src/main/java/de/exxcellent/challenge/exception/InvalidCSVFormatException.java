package de.exxcellent.challenge.exception;

import java.io.IOException;

public class InvalidCSVFormatException extends IOException{
    public InvalidCSVFormatException(String message)
    {
        super(message);
    }
}

