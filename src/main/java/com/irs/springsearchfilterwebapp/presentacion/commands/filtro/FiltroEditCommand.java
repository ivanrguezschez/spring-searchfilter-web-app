package com.irs.springsearchfilterwebapp.presentacion.commands.filtro;

import com.irs.springsearchfilterwebapp.presentacion.commands.BaseCommand;

/**
 * Clase Command empleado para la edición de un filtro.
 *
 * @author IRS
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public class FiltroEditCommand extends BaseCommand {

    /**
     * Nombre del filtro.
     */
    private String nombre;


    /**
     * Constructor por defecto.
     */
    public FiltroEditCommand() {
        super();
        this.nombre = null;
    }

    /**
     * Método que obtiene el nombre del filtro.
     *
     * @return Devuelve el nombre del filtro.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método que establece el nombre del filtro.
     *
     * @param nombre El nombre del filtro.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
