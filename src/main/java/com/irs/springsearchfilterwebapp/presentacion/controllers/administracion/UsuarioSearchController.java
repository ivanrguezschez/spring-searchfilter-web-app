package com.irs.springsearchfilterwebapp.presentacion.controllers.administracion;

import com.irs.springsearchfilterwebapp.negocio.servicios.RolService;
import com.irs.springsearchfilterwebapp.negocio.servicios.UsuarioService;
import com.irs.springsearchfilterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springsearchfilterwebapp.negocio.vo.FilterUsuarioVO;
import com.irs.springsearchfilterwebapp.negocio.vo.RolVO;
import com.irs.springsearchfilterwebapp.presentacion.ConstPresentacion;
import com.irs.springsearchfilterwebapp.presentacion.commands.administracion.UsuarioSearchCommand;
import com.irs.springsearchfilterwebapp.presentacion.controllers.BaseController;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"usuarioSearchCommand", "searchExecuted", "page"})
public class UsuarioSearchController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioSearchController.class);

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private RolService rolService;

    /**
     * Constructor.
     */
    public UsuarioSearchController() {
        super();
    }

    @ModelAttribute(name=ConstPresentacion.ROLES)
    public List<RolVO> populateRoles() throws ServiceException {
	return rolService.findAll();
    }
    
    @RequestMapping(value = "/administracion/usuarioSearchLoad.htm", method = RequestMethod.GET)
    public String usuarioSearchLoadHandler(ModelMap model,
            HttpServletRequest request) {
        UsuarioSearchCommand command = new UsuarioSearchCommand();
        model.put(ConstPresentacion.COMMAND_USUARIO_SEARCH_KEY, command);
        model.addAttribute(ConstPresentacion.SEARCH_EXECUTED_KEY, Boolean.FALSE);

        return ConstPresentacion.VIEW_NAME_USUARIO_SEACH_KEY;
    }

    @RequestMapping(value = "/administracion/usuarioSearch.htm")
    public ModelAndView usuarioSearchHandler(
            @ModelAttribute("usuarioSearchCommand") UsuarioSearchCommand command,
            ModelMap model, HttpServletRequest request) throws ServiceException {
        addSearchDataResult(model, command);
        model.addAttribute(ConstPresentacion.SEARCH_EXECUTED_KEY, Boolean.TRUE);

        return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_SEACH_KEY, model);
    }

    @RequestMapping(value = "/administracion/usuarioSearchReload.htm", method = RequestMethod.GET)
    public ModelAndView usuarioSearchReloadHandler(
            @ModelAttribute("usuarioSearchCommand") UsuarioSearchCommand command,
            ModelMap model, HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession(false);
        if (isSearchExecuted(session)) {
            addSearchDataResult(model, command);
            model.addAttribute(ConstPresentacion.SEARCH_EXECUTED_KEY, Boolean.TRUE);
        }

        return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_SEACH_KEY, model);
    }

    @RequestMapping(value = "/administracion/usuarioDelete.htm", method = RequestMethod.GET)
    public ModelAndView usuarioDeleteHandler(
            @RequestParam(value = "idUsuario", required = true) Integer idUsuario,
            @ModelAttribute("usuarioSearchCommand") UsuarioSearchCommand command,
            ModelMap model, HttpServletRequest request) throws ServiceException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Eliminando usuario '{}'", idUsuario);
        }
        usuarioService.remove(idUsuario);

        HttpSession session = request.getSession(false);
        session.setAttribute("globalMessage", getMessage("info.message.remove.usuario.success"));

        return new ModelAndView(ConstPresentacion.VIEW_NAME_USUARIO_SEACH_REDIRECT_KEY);
    }

    private void addSearchDataResult(ModelMap model, UsuarioSearchCommand command)
            throws ServiceException {
        final FilterUsuarioVO filter = (FilterUsuarioVO) toVo(command, FilterUsuarioVO.class);
        
        if (LOG.isDebugEnabled()) {
            LOG.debug("Buscando usuarios por filtro '{}'", filter);
        }
         
        model.put(ConstPresentacion.USUARIO_LIST_KEY, usuarioService.findByFilter(filter));
    }

    /**
     * Indica si se ha realizado una busqueda (true) o no (false).
     *
     * @param session Objeto session del usuario.
     * @return true si se ha realizado una busqueda, false en caso contrario.
     */
    private static boolean isSearchExecuted(HttpSession session) {
        Boolean searchExecuted = getBooleanAttribute(session,
                ConstPresentacion.SEARCH_EXECUTED_KEY, null);

        return (searchExecuted != null && searchExecuted.booleanValue());
    }

    private static Boolean getBooleanAttribute(HttpSession session, String attributeName, Boolean defaultValue) {
        Boolean result = (Boolean) session.getAttribute(attributeName);
        if (result == null) {
            LOG.warn(String.format("Valor session attribute '%s' null", attributeName));
            result = defaultValue;
        }

        return result;
    }
}
