package com.irs.springsearchfilterwebapp.persistencia.dao;

import com.irs.springsearchfilterwebapp.persistencia.dao.exceptions.DaoException;
import com.irs.springsearchfilterwebapp.negocio.vo.TipoEntidadVO;
import java.util.List;

/**
 * Interface de la capa de persistencia empleada para los tipos de entidad.
 *
 * @author IRS
 * @version 1.0.0
 */
public interface TipoEntidadDao {

    /**
     * Selecciona todos los tipos de entidad.
     *
     * @return Una colección de tipos de entidad.
     * @throws DaoException Si se produce algún error en la selección.
     */
    List<TipoEntidadVO> selectAll() throws DaoException;
}
