package com.rivera.clientreferences.exception;

import java.util.List;

/**
 * Created by Omar.Rivera on 05/3/2024.
 *
 * @author Omar.Rivera
 */

public class BusinessException extends RuntimeException{
    private List<ErrorModel> errors;

    public BusinessException() {
    }

    public BusinessException(List<ErrorModel> errors) {
        this.errors = errors;
    }

    public List<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorModel> errors) {
        this.errors = errors;
    }
}
