package com.irs.springsearchfilterwebapp.presentacion;

/**
 * Clase de constantes de la aplicación de la capa de presentación.
 * 
 * @author IRS
 * @version 1.0.0
 */
public final class ConstPresentacion {

    /**
     * Constructor privado.
     */
    private ConstPresentacion() {
    }

    public static final String APP_PROPERTIES_KEY = "appProperties";

    public static final String SEARCH_EXECUTED_KEY = "searchExecuted";

    public static final String ROLES = "roles";
    public static final String TIPOS_ENTIDAD = "tiposEntidad";
    public static final String FILTROS = "filtros";

    // Home
    public static final String VIEW_NAME_HOME_KEY = "home";
    public static final String VIEW_NAME_HOME_REDIRECT_KEY = "redirect:home.htm";


    // Administración Usuarios
    public static final String VIEW_NAME_USUARIO_SEACH_KEY = "usuarioSearch";
    public static final String VIEW_NAME_USUARIO_EDIT_KEY = "usuarioEdit";
    public static final String VIEW_NAME_USUARIO_VIEW_KEY = "usuarioView";
    public static final String VIEW_NAME_USUARIO_SEACH_REDIRECT_KEY = "redirect:usuarioSearchReload.htm";
    public static final String VIEW_NAME_USUARIO_LIST_PDF_KEY = "usuarioListPdfView";
    public static final String VIEW_NAME_USUARIO_LIST_XLS_KEY = "usuarioListXlsView";
    public static final String COMMAND_USUARIO_SEARCH_KEY = "usuarioSearchCommand";
    public static final String COMMAND_USUARIO_EDIT_KEY = "usuarioEditCommand";
    public static final String USUARIO_LIST_KEY = "usuarioList";
    public static final String USUARIO_KEY = "usuario";
}
