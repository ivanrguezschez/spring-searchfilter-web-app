package com.irs.springsearchfilterwebapp.negocio.servicios.impl;

import com.irs.springsearchfilterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springsearchfilterwebapp.negocio.vo.UsuarioVO;
import com.irs.springsearchfilterwebapp.persistencia.dao.exceptions.DaoException;
import com.irs.springsearchfilterwebapp.negocio.servicios.FiltroService;
import com.irs.springsearchfilterwebapp.negocio.vo.FiltroVO;
import com.irs.springsearchfilterwebapp.persistencia.dao.FiltroDao;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementanción de la interface de la logica de negocio empleada para los
 * filtros.
 *
 * @author IRS
 * @version 1.0.0
 */
@Service("com.irs.springbootstrapvalidationwebapp.negocio.servicios.FiltroService")
public class FiltroServiceImpl implements FiltroService {

    private static final Logger LOG = LoggerFactory.getLogger(FiltroServiceImpl.class);

    /**
     * Objeto de persistencia de filtro.
     */
    @Autowired
    private FiltroDao filtroDao;

    /**
     * Constructor por defecto.
     */
    public FiltroServiceImpl() {
        super();
    }

    /**
     * Busca filtros por usuario.
     *
     * @param usuario Filtro empleado en la busqueda.
     * @return Una lista con los filtro del usuario.
     * @throws ServiceException Si se produce un error en la busqueda.
     */
    /*
    public List<FiltroVO> findByUsuario(UsuarioVO usuario) throws ServiceException {
         List<FiltroVO> result = null;

        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Buscando filtros por usuario '{}'", usuario);
            }
            result = filtroDao.selectByUsuario(usuario);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(e);
        }

        return result;
    }
    */
    public List<FiltroVO> findAll() throws ServiceException {
         List<FiltroVO> result = null;

        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Buscando todos los filtros");
            }
            result = filtroDao.selectAll();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(e);
        }

        return result;
    }

    /**
     * Busca un filtro por identificador.
     *
     * @param idFiltro identificador del filtro a buscar.
     * @return El filtro con el identificador especificado.
     * @throws ServiceException Si se produce un error en la busqueda.
     */
    public FiltroVO findById(final Integer idFiltro) throws ServiceException {
        FiltroVO result = null;

        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Buscando filtro por id '{}'", idFiltro);
            }
            result = filtroDao.selectById(idFiltro);
        } catch (DaoException e) {
            LOG.error(e.getMessage());
            throw new ServiceException(e);
        }

        return result;
    }

    /**
     * Guarda un nuevo filtro.
     *
     * @param filtro el nuevo filtro a guardar.
     * @return El nuevo filtro guardado.
     * @throws ServiceException Si se produce un error al guardar el filtro.
     */
    public FiltroVO save(FiltroVO filtro) throws ServiceException {
        FiltroVO result = null;

        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Guardando filtro");
            }

            result = filtroDao.insert(filtro);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(e);
        }

        return result;
    }

    /**
     * Borra un filtro (Borrado físico).
     *
     * @param idFiltro identificador del filtro a borrar.
     * @throws ServiceException Si se produce un error en el borrado del
     * filtro.
     */
    public void remove(final Integer idFiltro) throws ServiceException {
        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Eliminando filtro por id '{}'", idFiltro);
            }

            filtroDao.delete(filtroDao.selectById(idFiltro));
        } catch (DaoException e) {
            LOG.error(e.getMessage());
            throw new ServiceException(e);
        }
    }
}
