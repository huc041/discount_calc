package com.learning.discount_calculator.exceptions;

import java.io.IOException;

public class WriteOrderIOException extends IOException {
    public WriteOrderIOException (String message, Throwable cause){
        super(message, cause);
    }
}
