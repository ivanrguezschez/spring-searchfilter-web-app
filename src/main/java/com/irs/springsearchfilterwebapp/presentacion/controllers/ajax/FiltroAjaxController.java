package com.irs.springsearchfilterwebapp.presentacion.controllers.ajax;

import com.irs.springsearchfilterwebapp.negocio.servicios.FiltroService;
import com.irs.springsearchfilterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springsearchfilterwebapp.negocio.vo.FiltroVO;
import com.irs.springsearchfilterwebapp.negocio.vo.TipoEntidadVO;
import com.irs.springsearchfilterwebapp.presentacion.commands.filtro.FiltroEditCommand;
import com.irs.springsearchfilterwebapp.presentacion.controllers.BaseController;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller Ajax de Filtros
 *
 * @author SIGE
 * @version 1.0.0
 */
@Controller
public class FiltroAjaxController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(FiltroAjaxController.class);

    @Autowired
    private FiltroService filtroService;

    @Autowired
    @Qualifier("filtroEditCommandValidator")
    private Validator validator;
    
    /**
     * Constructor.
     */
    public FiltroAjaxController() {
        super();
    }

    @RequestMapping("/ajax/filtroSearchAjax.htm")
    @ResponseBody
    public List<FiltroVO> filtroSearchAjaxHandler() throws ServiceException {
        List<FiltroVO> result = filtroService.findAll();
        
        for (FiltroVO filtro : result) {
            LOG.debug(filtro.toString());
        }
                
        return result;
    }
    
    @RequestMapping("/ajax/filtroSearchByIdAjax.htm")
    @ResponseBody
    public FiltroVO filtroSearchByIdAjaxHandler(
            @RequestParam(value = "idFiltro", required = true) Integer idFiltro)
            throws ServiceException {
        return filtroService.findById(idFiltro);
    }
    
    @RequestMapping("/ajax/filtroSaveValidateAjax.htm")
    @ResponseBody
    public List<String> filtroSaveValidateAjaxHandler(
            @RequestParam(value = "nombre", required = true) String nombre) {
        List<String> errorsMessage = new ArrayList<String>();

        FiltroEditCommand command = buildCommand(nombre);
        if (LOG.isDebugEnabled()) {
            LOG.debug("Validando command filtro: " + command);
        }

        BeanPropertyBindingResult result = new BeanPropertyBindingResult(command, "filtroEditCommand");
        validator.validate(command, result);
        if (result.hasErrors()) {
            for (ObjectError oe : result.getAllErrors()) {
                if (oe.getArguments() != null) {
                    errorsMessage.add(getMessage(oe.getCode(), oe.getArguments()));
                } else {
                    errorsMessage.add(getMessage(oe.getCode()));
                }
            }
        }
        
        return errorsMessage;
    }
  
    @RequestMapping("/ajax/filtroSaveAjax.htm")
    @ResponseBody
    public FiltroVO filtroSaveAjaxHandler(
            @RequestParam(value = "idTipoEntidad", required = true) String idTipoEntidad,
            @RequestParam(value = "nombre", required = true) String nombre,
            //@RequestParam(value = "idUsuario", required = true) Integer idUsuario,
            @RequestParam(value = "valor", required = true) String valor) throws ServiceException {
        
        TipoEntidadVO tipoEntidad = new TipoEntidadVO();
        tipoEntidad.setIdTipoEntidad(Integer.parseInt(idTipoEntidad));
        
        FiltroVO filtro = new FiltroVO();
        filtro.setNombre(nombre);
        filtro.setTipoEntidad(tipoEntidad);
        filtro.setValor(valor);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Guardando filtro '{}'", filtro);
        }

        filtro = filtroService.save(filtro);

        return filtro;
    }

    @RequestMapping("/ajax/filtroDeleteAjax.htm")
    @ResponseBody
    public void filtroDeleteAjaxHandler(
            @RequestParam(value = "idFiltro", required = true) Integer idFiltro)
            throws ServiceException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Eliminando filtro '" + idFiltro + "'");
        }
        filtroService.remove(idFiltro);
    }
    
    private FiltroEditCommand buildCommand(String nombre) {
        FiltroEditCommand command = new FiltroEditCommand();

        command.setNombre(nombre);

        return command;
    }

}
