package com.irs.springsearchfilterwebapp.negocio.vo;


/**
 * Clase de negocio Value Object (VO) empleado para almacenar los tipos de entidad.
 *
 * @author IRS
 * @version 1.0.0
 */
public class TipoEntidadVO extends BaseVO implements Identifiable<Integer> {

    /**
     * Identificador del tipo de entidad.
     */
    private Integer idTipoEntidad;

    /**
     * Nombre del tipo de entidad.
     */
    private String nombre;

    /**
     * Constructor por defecto.
     */
    public TipoEntidadVO() {
        super();
        this.idTipoEntidad = null;
        this.nombre = null;
    }

    /**
     * Método que obtiene el identificador del tipo de entidad.
     *
     * @return Devuelve el identificador del tipo de entidad.
     */
    public Integer getIdTipoEntidad() {
        return idTipoEntidad;
    }

    /**
     * Método que establece el identificador del tipo de entidad.
     *
     * @param idTipoEntidad El identificador del tipo de entidad.
     */
    public void setIdTipoEntidad(Integer idTipoEntidad) {
        this.idTipoEntidad = idTipoEntidad;
    }

    /**
     * Método que obtiene el nombre del tipo de entidad.
     *
     * @return Devuelve el nombre del tipo de entidad.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Método que establece el nombre del tipo de entidad.
     *
     * @param nombre El nombre del tipo de entidad.
     */
    public void setNombre(String nombre) {    
        this.nombre = nombre;
    }

    /**
     * Método que obtiene el id del tipo de entidad.
     *
     * @return Devuelve el id del tipo de entidad.
     */
    public Integer getId() {
        return getIdTipoEntidad();
    }

    /**
     * Método que establece el id del tipo de entidad.
     *
     * @param id El id del tipo de entidad.
     */
    public void setId(Integer id) {
        setIdTipoEntidad(id);
    }

    /**
     * Método que obtiene el nombre del id del tipo de entidad.
     *
     * @return Devuelve el nombre del id del tipo de entidad.
     */
    public String getIdName() {
        return "idTipoEntidad";
    }

    /**
     * Método que indica si el tipo de entidad es nuevo (true) o no (false).
     *
     * @return Devuelve true si el tipo de entidad es nuevo, false en caso contrario.
     */
    public boolean isNew() {
        return (getIdTipoEntidad() == null) || (getIdTipoEntidad().intValue() == 0);
    }
}
