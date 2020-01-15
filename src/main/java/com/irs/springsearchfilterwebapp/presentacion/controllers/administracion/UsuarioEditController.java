package com.irs.springsearchfilterwebapp.presentacion.controllers.administracion;

import com.irs.springsearchfilterwebapp.negocio.servicios.RolService;
import com.irs.springsearchfilterwebapp.negocio.servicios.UsuarioService;
import com.irs.springsearchfilterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springsearchfilterwebapp.negocio.vo.RolVO;
import com.irs.springsearchfilterwebapp.negocio.vo.UsuarioVO;
import com.irs.springsearchfilterwebapp.presentacion.ConstPresentacion;
import com.irs.springsearchfilterwebapp.presentacion.commands.administracion.UsuarioEditCommand;
import com.irs.springsearchfilterwebapp.presentacion.controllers.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioEditController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioEditController.class);

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;
     
    @Autowired
    @Qualifier("usuarioEditCommandValidator")
    private Validator validator;

    /**
     * Constructor.
     */
    public UsuarioEditController() {
        super();
    }

    @ModelAttribute(name=ConstPresentacion.ROLES)
    public List<RolVO> populateRoles() throws ServiceException {
	return rolService.findAll();
    }
    
    @RequestMapping(value = "/administracion/usuarioEdit.htm", method = RequestMethod.GET)
    public ModelAndView usuarioEditHandler(
            @RequestParam(value = "idUsuario", required = false) Integer idUsuario,
            HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession(false);
        ModelMap model = new ModelMap();
        UsuarioEditCommand command = new UsuarioEditCommand();

        session.removeAttribute("globalError");
        session.removeAttribute("globalMessage");

        if (idUsuario != null) {
            UsuarioVO usuario = usuarioService.findById(idUsuario);
            command = (UsuarioEditCommand) toCommand(usuario, UsuarioEditCommand.class);
            model.addAttribute(ConstPresentacion.USUARIO_KEY, usuario);
        }

        model.addAttribute(ConstPresentacion.COMMAND_USUARIO_EDIT_KEY, command);

        return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_EDIT_KEY, model);
    }

    @RequestMapping(value = "/administracion/usuarioEditSubmit.htm", method = RequestMethod.POST)
    public ModelAndView usuarioEditSubmitHandler(
            @ModelAttribute("usuarioEditCommand") UsuarioEditCommand command,
            BindingResult result, SessionStatus status,
            HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession(false);
        ModelMap model = new ModelMap();
        UsuarioVO usuario = null;
        String messageKey = null;

        if (isCancelled(request)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Cancelando la edicion del usuario");
            }
            return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_SEACH_REDIRECT_KEY);
        }

        validator.validate(command, result);
        if (!result.hasErrors()) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Comando UsuarioEditCommand valido");
            }
            usuario = (UsuarioVO) toVo(command, UsuarioVO.class);
            if (usuario.isNew()) {
                usuario = usuarioService.save(usuario);
                messageKey = "info.message.save.usuario.success";
            } else {
                usuarioService.update(usuario);
                messageKey = "info.message.update.usuario.success";
            }
            session.setAttribute("globalMessage", getMessage(messageKey));
            status.setComplete();

            return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_SEACH_REDIRECT_KEY, model);
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Comando UsuarioEditCommand invalido");
            }
            return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_EDIT_KEY, model);
        }
    }

    @RequestMapping(value = "/administracion/usuarioView.htm", method = RequestMethod.GET)
    public ModelAndView usuarioViewHandler(
            @RequestParam(value = "idUsuario", required = true) Integer idUsuario,
            HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession(false);
        
        session.removeAttribute("globalError");
        session.removeAttribute("globalMessage");

        ModelMap model = new ModelMap();
        UsuarioVO usuario = usuarioService.findById(idUsuario);
        model.addAttribute(ConstPresentacion.USUARIO_KEY, usuario);

        return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_VIEW_KEY, model);
    }

    /**
     * Método que indica si en la request existe un parametro 'cancelar' lo cual
     * nos indica que el usuario pulso el botón cancelar.
     *
     * @param request Objeto request.
     * @return true si existe un parametro 'cancelar', false en caso contrario.
     */
    private static boolean isCancelled(HttpServletRequest request) {
        return (request.getParameter("cancelar") != null);
    }
}
