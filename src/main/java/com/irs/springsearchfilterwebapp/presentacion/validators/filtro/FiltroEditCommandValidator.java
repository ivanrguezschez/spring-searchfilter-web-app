package com.irs.springsearchfilterwebapp.presentacion.validators.filtro;

import com.irs.springsearchfilterwebapp.presentacion.commands.filtro.FiltroEditCommand;
import com.irs.springsearchfilterwebapp.presentacion.validators.BaseValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component("filtroEditCommandValidator")
public class FiltroEditCommandValidator extends BaseValidator {

    private static final Logger LOG = LoggerFactory.getLogger(FiltroEditCommandValidator.class);

    private static final String ERROR_CODE_REQUIRED = "required";
    
    private static final String NOMBRE = "nombre";
    
    /**
     * Constructor.
     */
    public FiltroEditCommandValidator() {
        super();
    }

    public boolean supports(Class<?> candidate) {
        return FiltroEditCommand.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {
        // Valido el campo nombre
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                NOMBRE,
                ERROR_CODE_REQUIRED,
                new Object[]{getMessage("label.nombreFiltro")});

    }
}
