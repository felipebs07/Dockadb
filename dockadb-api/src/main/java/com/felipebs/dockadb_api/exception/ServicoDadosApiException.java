package com.felipebs.dockadb_api.exception;

public class ServicoDadosApiException extends Exception {

    public ServicoDadosApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServicoDadosApiException(String message) {
        super(message);
    }
}
