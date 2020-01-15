package com.irs.springsearchfilterwebapp.presentacion.commands.administracion;

import com.irs.springsearchfilterwebapp.presentacion.commands.BaseCommand;
import java.util.Date;

/**
 * Clase Command empleado como filtro para la busqueda de usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class UsuarioSearchCommand extends BaseCommand {

    /**
     * Nif del usuario.
     */
    private String nif;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Primer apellido del usuario.
     */
    private String apellido1;

    /**
     * Segundo apellido del usuario.
     */
    private String apellido2;

    /**
     * Rol del usuario.
     */
    private Integer rol;

    /**
     * Identifiador del filtro de búsqueda seleccionado.
     */
    private Integer idFiltro;
    
    /**
     * Constructor por defecto.
     */
    public UsuarioSearchCommand() {
        super();
        this.nif = null;
        this.nombre = null;
        this.apellido1 = null;
        this.apellido2 = null;
        this.rol = null;
        this.idFiltro = null;
    }

    /**
     * Método que obtiene el nif del usuario.
     *
     * @return Devuelve el nif del usuario.
     */
    public String getNif() {
        return nif;
    }

    /**
     * Método que establece el nif del usuario.
     *
     * @param nif El nif del usuario.
     */
    public void setNif(String nif) {
        this.nif = nif;
    }

    /**
     * Método que obtiene el nombre del usuario.
     *
     * @return Devuelve el nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el primer apellido del usuario.
     *
     * @return Devuelve el primer apellido del usuario.
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Método que establece el primer apellido del usuario.
     *
     * @param apellido1 El primer apellido del usuario.
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Método que obtiene el segundo apellido del usuario.
     *
     * @return Devuelve el segundo apellido del usuario.
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Método que establece el segundo apellido del usuario.
     *
     * @param apellido2 El segundo apellido del usuario.
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Método que obtiene el rol del usuario.
     *
     * @return Devuelve el rol del usuario.
     */
    public Integer getRol() {
        return rol;
    }

    /**
     * Método que establece el rol del usuario.
     *
     * @param rol El rol del usuario.
     */
    public void setRol(Integer rol) {
        this.rol = rol;
    }

    public Integer getIdFiltro() {
        return idFiltro;
    }

    public void setIdFiltro(Integer idFiltro) {
        this.idFiltro = idFiltro;
    }
}
