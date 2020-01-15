package com.irs.springsearchfilterwebapp.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Clase del dominio empleada para almacenar los tipos de entidad.
 *
 * @author IRS
 * @version 1.0.0
 */
@Entity
@Table(name = "TIPO_ENTIDAD")
public class TipoEntidad extends BaseEntity implements Identifiable<Integer> {

    /**
     * Identificador del tipo de entidad.
     */
    @Id
    @Column(name = "ID_TIPO_ENTIDAD", unique = true, nullable = false, precision = 8, scale = 0)
    private Integer idTipoEntidad;

    /**
     * Nombre del tipo de entidad.
     */
    @Column(name = "NOMBRE", unique = true, nullable = false, length = 50)
    private String nombre;

    /**
     * Constructor por defecto.
     */
    public TipoEntidad() {
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
     * Método que obtiene la clave primaria del tipo de entidad.
     *
     * @return Devuelve la clave primaria del tipo de entidad.
     */
    @Transient
    public Integer getPrimaryKey() {
        return getIdTipoEntidad();
    }

    /**
     * MÃ©todo que establece la clave primaria del tipo de entidad.
     *
     * @param pk La clave primaria del tipo de entidad.
     */
    @Transient
    public void setPrimaryKey(Integer pk) {
        setIdTipoEntidad(pk);
    }

    /**
     * MÃ©todo que obtiene el nombre de la clave primaria del rol.
     *
     * @return Devuelve el nombre de la clave primaria del rol.
     */
    @Transient
    public String getPrimaryKeyName() {
        return "idTipoEntidad";
    }

    /**
     * MÃ©todo que indica si el tipo de entidad es nuevo (true) o no (false).
     *
     * @return Devuelve true si el de entidad es nuevo, false en caso contrario.
     */
    @Transient
    public boolean isNew() {
        return getIdTipoEntidad() == null;
    }
}
