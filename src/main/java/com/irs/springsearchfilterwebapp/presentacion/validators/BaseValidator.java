package com.irs.springsearchfilterwebapp.presentacion.validators;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.Validator;

/**
 * Clase abstracta base de Spring MVC Validator.
 *
 * @author IRS
 * @version 1.0.0
 */
public abstract class BaseValidator implements Validator {

    @Autowired
    protected MessageSource messageSource;

    /**
     * Constructor.
     */
    public BaseValidator() {
        super();
    }

    protected String getMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, null);
    }

    protected String getMessage(String messageKey, Object[] messageArgs) {
        return messageSource.getMessage(messageKey, messageArgs, null);
    }

    protected String getMessage(String messageKey, Object[] messageArgs, Locale locale) {
        return messageSource.getMessage(messageKey, messageArgs, locale);
    }
}
