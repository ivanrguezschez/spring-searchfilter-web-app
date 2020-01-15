package com.irs.springsearchfilterwebapp.negocio.vo;

import java.util.Date;

/**
 * Clase de negocio Value Object (VO) empleado para almacenar el filtro de los
 * usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class FilterUsuarioVO extends BaseVO {

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
    private RolVO rol;

    /**
     * Indica si el usuario esta dado de baja (true) o no (false).
     */
    //private boolean baja;

    /**
     * Fecha de Alta desde.
     */
    //private Date fechaAltaDesde;

    /**
     * Fecha de Alta hasta.
     */
    //private Date fechaAltaHasta;

    /**
     * Constructor por defecto.
     */
    public FilterUsuarioVO() {
        super();
        this.nif = null;
        this.nombre = null;
        this.apellido1 = null;
        this.apellido2 = null;
        this.rol = null;
        //this.baja = false;
        //this.fechaAltaDesde = null;
        //this.fechaAltaHasta = null;
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
    public RolVO getRol() {
        return rol;
    }

    /**
     * Método que establece el rol del usuario.
     *
     * @param rol El rol del usuario.
     */
    public void setRol(RolVO rol) {
        this.rol = rol;
    }

    /**
     * Método que indica si el usuario esta dado de baja (true) o no (false).
     *
     * @return Devuelve si el usuario esta dado de baja (true) o no (false).
     */
//    public boolean isBaja() {
//        return baja;
//    }

    /**
     * Método que establece si el usuario esta dado de baja o no.
     *
     * @param baja Indica si el usuario esta dado de baja (true) o no (false).
     */
//    public void setBaja(boolean baja) {
//        this.baja = baja;
//    }

    /**
     * Método que obtiene la fecha de alta desde del usuario.
     *
     * @return Devuelve la fecha de alta desde del usuario.
     */
//    public Date getFechaAltaDesde() {
//        return fechaAltaDesde;
//    }

    /**
     * Método que establece la fecha de alta desde del usuario.
     *
     * @param fechaAltaDesde La fecha de alta desde del usuario.
     */
//    public void setFechaAltaDesde(Date fechaAltaDesde) {
//        this.fechaAltaDesde = fechaAltaDesde;
//    }

    /**
     * Método que obtiene la fecha de alta hasta del usuario.
     *
     * @return Devuelve la fecha de alta hasta.
     */
//    public Date getFechaAltaHasta() {
//        return fechaAltaHasta;
//    }

    /**
     * Método que establece la fecha de alta hasta del usuario.
     *
     * @param fechaAltaHasta La fecha de alta hasta del usuario.
     */
//    public void setFechaAltaHasta(Date fechaAltaHasta) {
//        this.fechaAltaHasta = fechaAltaHasta;
//    }
}
