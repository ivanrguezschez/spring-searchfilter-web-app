package com.irs.springsearchfilterwebapp.dominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

/**
 * Clase del dominio empleada para almacenar los filtros.
 *
 * @author IRS
 * @version 1.0.0
 */
@Entity
@Table(name = "FILTRO")
@SuppressWarnings("serial")
public class Filtro extends BaseEntity implements Identifiable<Integer> {

    /**
     * Identificador del filtro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FILTRO", unique = true, nullable = false, precision = 8, scale = 0)
    private Integer idFiltro;

    /**
     * Nombre del filtro.
     */
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    /**
     * Tipo de entidad del filtro.
     */
    @ManyToOne
    @JoinColumn(name = "ID_TIPO_ENTIDAD", nullable = false)
    private TipoEntidad tipoEntidad;
    
    
    // LO COMENTO PORQUE EN EL EJEMPLO NO HAY LOGIN, lo suyo seria guardarlos por usuario
    
    /**
     * Usuario propietario del filtro.
     */
//    @ManyToOne
//    @JoinColumn(name = "ID_USUARIO", nullable = false)
//    private Usuario usuario;
    
    /**
     * Valor del filtro (Representación del objeto de las propiedades del filtro en formato JSON).
     */
    @Column(name = "VALOR", nullable = false, length = 500)
    private String valor;

    /**
     * Constructor por defecto.
     */
    public Filtro() {
        super();
        this.idFiltro = null;
        this.nombre = null;
        this.nombre = null;
        this.tipoEntidad = null;
//        this.usuario = null;
        this.valor = null;
    }

    public Integer getIdFiltro() {
        return idFiltro;
    }

    public void setIdFiltro(Integer idFiltro) {
        this.idFiltro = idFiltro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoEntidad getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(TipoEntidad tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

//    public Usuario getUsuario() {
//        return usuario;
//    }

//    public void setUsuario(Usuario usuario) {
//        this.usuario = usuario;
//    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Método que obtiene la clave primaria del filtro.
     *
     * @return Devuelve la clave primaria del filtro.
     */
    @Transient
    public Integer getPrimaryKey() {
        return getIdFiltro();
    }

    /**
     * Método que establece la clave primaria del filtro.
     *
     * @param pk La clave primaria del filtro.
     */
    @Transient
    public void setPrimaryKey(Integer pk) {
        setIdFiltro(pk);
    }

    /**
     * Método que obtiene el nombre de la clave primaria del filtro.
     *
     * @return Devuelve el nombre la clave primaria del filtro.
     */
    @Transient
    public String getPrimaryKeyName() {
        return "idFiltro";
    }

    /**
     * Método que indica si el filtro es nuevo (true) o no (false).
     *
     * @return Devuelve true si el filtro es nuevo, false en caso contrario.
     */
    @Transient
    public boolean isNew() {
        return getIdFiltro() == null;
    }
}
