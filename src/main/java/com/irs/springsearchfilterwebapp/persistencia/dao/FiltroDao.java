package com.irs.springsearchfilterwebapp.persistencia.dao;

import com.irs.springsearchfilterwebapp.negocio.vo.FiltroVO;
import com.irs.springsearchfilterwebapp.persistencia.dao.exceptions.DaoException;
import com.irs.springsearchfilterwebapp.negocio.vo.UsuarioVO;
import java.util.List;

/**
 * Interface de la capa de persistencia empleada para los filtros.
 *
 * @author IRS
 * @version 1.0.0
 */
public interface FiltroDao {

    //List<FiltroVO> selectByUsuario(final UsuarioVO usuario) throws DaoException;
    List<FiltroVO> selectAll() throws DaoException;
    
    FiltroVO selectById(Integer idFiltro) throws DaoException;

    FiltroVO insert(FiltroVO filtro) throws DaoException;

    void delete(FiltroVO filtro) throws DaoException;
}
