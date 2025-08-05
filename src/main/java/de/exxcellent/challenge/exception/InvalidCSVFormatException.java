package de.exxcellent.challenge.exception;

import java.io.IOException;

/**
 * Should be thrown if a CSV file does not have the required format
 */
public class InvalidCSVFormatException extends IOException{
    public InvalidCSVFormatException(String message)
    {
        super(message);
    }
}

