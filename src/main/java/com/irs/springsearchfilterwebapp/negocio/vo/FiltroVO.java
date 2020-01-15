package com.irs.springsearchfilterwebapp.negocio.vo;

/**
 * Clase del dominio empleada para almacenar los filtros.
 *
 * @author IRS
 * @version 1.0.0
 */
public class FiltroVO extends BaseVO implements Identifiable<Integer> {

    /**
     * Identificador del filtro.
     */
    private Integer idFiltro;

    /**
     * Nombre del filtro.
     */
    private String nombre;

    /**
     * Tipo de entidad del filtro.
     */
    private TipoEntidadVO tipoEntidad;
    
    // LO COMENTO PORQUE EN EL EJEMPLO NO HAY LOGIN, lo suyo seria guardarlos por usuario
    
    /**
     * Usuario propietario del filtro.
     */
    //private UsuarioVO usuario;
    
    /**
     * Valor del filtro (Representación del objeto de las propiedades del filtro en formato JSON).
     */
    private String valor;

    /**
     * Constructor por defecto.
     */
    public FiltroVO() {
        super();
        this.idFiltro = null;
        this.nombre = null;
        this.nombre = null;
        this.tipoEntidad = null;
        //this.usuario = null;
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

    public TipoEntidadVO getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(TipoEntidadVO tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

//    public UsuarioVO getUsuario() {
//        return usuario;
//    }

//    public void setUsuario(UsuarioVO usuario) {
//        this.usuario = usuario;
//    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * Método que obtiene el id del filtro.
     *
     * @return Devuelve el id del filtro.
     */
    public Integer getId() {
        return getIdFiltro();
    }

    /**
     * Método que establece el id del filtro.
     *
     * @param id El id del filtro.
     */
    public void setId(Integer id) {
        setIdFiltro(id);
    }

    /**
     * Método que obtiene el nombre del id del filtro.
     *
     * @return Devuelve el nombre del id del filtro.
     */
    public String getIdName() {
        return "idFiltro";
    }

    /**
     * Método que indica si el filtro es nuevo (true) o no (false).
     *
     * @return Devuelve true si el filtro es nuevo, false en caso contrario.
     */
    public boolean isNew() {
        return (getIdFiltro() == null) || (getIdFiltro().intValue() == 0);
    }
}
