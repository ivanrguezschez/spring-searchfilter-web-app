package com.irs.springsearchfilterwebapp.negocio.servicios;

import com.irs.springsearchfilterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springsearchfilterwebapp.negocio.vo.FiltroVO;
import com.irs.springsearchfilterwebapp.negocio.vo.UsuarioVO;
import java.util.List;

/**
 * Interface de la lógica de negocio empleada para los filtros.
 *
 * @author IRS
 * @version 1.0.0
 */
public interface FiltroService {

    /**
     * Busca filtros por usuario.
     *
     * @param usuario Usuario cuyos filtros se quieren buscar.
     * @return Una lista con los filtros que cumplan el filtro de busqueda.
     * @throws ServiceException Si se produce un error en la busqueda.
     */
    //List<FiltroVO> findByUsuario(UsuarioVO usuario) throws ServiceException;
    List<FiltroVO> findAll() throws ServiceException;

    /**
     * Busca un filtro por identificador.
     *
     * @param idFiltro identificador del filtro a buscar.
     * @return El filtro con el identificador especificado.
     * @throws ServiceException Si se produce un error en la busqueda.
     */
    FiltroVO findById(final Integer idFiltro) throws ServiceException;

    /**
     * Guarda un nuevo filtro.
     *
     * @param filtro el nuevo filtro a guardar.
     * @return El nuevo filtro guardado.
     * @throws ServiceException Si se produce un error al guardar el filtro.
     */
    FiltroVO save(final FiltroVO filtro) throws ServiceException;

    /**
     * Borra un filtro (Borrado físico).
     *
     * @param idFiltro identificador del filtro a borrar.
     * @throws ServiceException Si se produce un error en el borrado del filtro.
     */
    void remove(final Integer idFiltro) throws ServiceException;
}
