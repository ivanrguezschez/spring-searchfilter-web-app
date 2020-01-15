package com.irs.springsearchfilterwebapp.presentacion.validators.administracion;

import com.irs.springsearchfilterwebapp.presentacion.commands.administracion.UsuarioEditCommand;
import com.irs.springsearchfilterwebapp.presentacion.util.NifUtil;
import com.irs.springsearchfilterwebapp.presentacion.validators.BaseValidator;
import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

@Component("usuarioEditCommandValidator")
public class UsuarioEditCommandValidator extends BaseValidator {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioEditCommandValidator.class);

    private static final String ERROR_CODE_REQUIRED = "required";
    private static final String ERROR_CODE_INVALID = "invalid";

    private static final String EMAIL_REGEX
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    private static final String NIF = "nif";
    private static final String NOMBRE = "nombre";
    private static final String APELLIDO1 = "apellido1";
    private static final String APELLIDO2 = "apellido2";
    private static final String EMAIL = "email";
    private static final String ROL = "rol";
    private static final String FECHA_ALTA = "fechaAlta";

//    @Autowired
//    private UsuarioService usuarioService;
    /**
     * Constructor.
     */
    public UsuarioEditCommandValidator() {
        super();
    }

    public boolean supports(Class<?> candidate) {
        return UsuarioEditCommand.class.isAssignableFrom(candidate);
    }

    public void validate(Object obj, Errors errors) {
        UsuarioEditCommand command = (UsuarioEditCommand) obj;

        // Valido el campo nif
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                NIF,
                ERROR_CODE_REQUIRED,
                new Object[]{getMessage("label.nif")});

        // Valido que sea un nif/nie/nit válido
        if (!errors.hasFieldErrors(NIF)) {
            rejectIfNotNif(errors,
                    NIF,
                    ERROR_CODE_INVALID,
                    new Object[] { getMessage("label.nif") },
                    null);
        }

        // Valido que no sea un nif que ya existe
//        if (!errors.hasFieldErrors(Const.FN_NIF)) {
//            try {
//                Integer idUsuario = usuarioService.existNif(command.getNif());
//                if (idUsuario != null
//                        && (command.getIdUsuario() == null
//                        || (command.getIdUsuario() != null && idUsuario.intValue() != command.getIdUsuario().intValue()))) {
//                    // Intento modificar el nif introduciendo otro que ya existe o insertar uno que ya existe
//                    LOG.debug("El nif '" + command.getNif() + "' ya existe");
//                    errors.rejectValue(Const.FN_NIF, "error.nif.duplicate");
//                }
//            } catch (ServiceException e) {
//                LOG.warn(e.getMessage());
//            }
//        }
        // Valido el campo nombre
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                NOMBRE,
                ERROR_CODE_REQUIRED,
                new Object[]{getMessage("label.nombre")});

        // Valido el campo apellido1
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                APELLIDO1,
                ERROR_CODE_REQUIRED,
                new Object[]{getMessage("label.apellido1")});

        // Valido el campo apellido2
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                APELLIDO2,
                ERROR_CODE_REQUIRED,
                new Object[]{getMessage("label.apellido2")});
        
        // Valido el campo email
        if (StringUtils.isNotEmpty(command.getEmail())) {
            rejectIfNotMask(errors,
                    EMAIL,
                    EMAIL_REGEX,
                    ERROR_CODE_INVALID,
                    new Object[]{getMessage("label.email")},
                    null);
        }

        // Valido el campo rol
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                ROL,
                ERROR_CODE_REQUIRED,
                new Object[]{getMessage("label.rol")});
        
        // Valido el campo fechaAlta
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,
                FECHA_ALTA,
                ERROR_CODE_REQUIRED,
                new Object[]{getMessage("label.fechaAlta")});
    }

    /**
     * Comprueba si el valor del campo es un NIF válido.
     *
     * @param errors almacena los errores del campo.
     * @param field nombre del campo.
     * @param errorCode código del error.
     * @param errorArgs argumentos del mensaje de error.
     * @param defaultMessage mensaje por defecto.
     */
    private static void rejectIfNotNif(Errors errors, String field,
            String errorCode, Object[] errorArgs, String defaultMessage) {
        Object value = errors.getFieldValue(field);
        if (value != null) {
            String valueString = (String) value;

            if (LOG.isDebugEnabled()) {
                LOG.debug("Validando Nif '{}'", valueString);
            }

            if (!NifUtil.esNif(valueString)) {
                errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
            }
        }
    }

    /**
     * Comprueba si el valor del campo cumple con la mascara mask.
     *
     * @param errors almacena los errores del campo.
     * @param field nombre del campo.
     * @param mask mascara con la cual se comprueba el valor del campo.
     * @param errorCode código del error.
     * @param errorArgs argumentos del mensaje de error.
     * @param defaultMessage mensaje por defecto.
     */
    private static void rejectIfNotMask(Errors errors, String field,
            String mask, String errorCode, Object[] errorArgs, String defaultMessage) {
        Object value = errors.getFieldValue(field);
        String valueString = null;
        if (value instanceof String) {
            valueString = (String) value;
        } else {
            valueString = value.toString();
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Validando '{}' contra la mascara '{}'", valueString, mask);
        }

        if (!Pattern.matches(mask, valueString)) {
            errors.rejectValue(field, errorCode, errorArgs, defaultMessage);
        }
    }
}
