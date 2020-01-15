package com.irs.springsearchfilterwebapp.negocio.servicios.impl;

import com.irs.springsearchfilterwebapp.negocio.servicios.UsuarioService;
import com.irs.springsearchfilterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springsearchfilterwebapp.negocio.vo.FilterUsuarioVO;
import com.irs.springsearchfilterwebapp.negocio.vo.UsuarioVO;
import com.irs.springsearchfilterwebapp.persistencia.dao.UsuarioDao;
import com.irs.springsearchfilterwebapp.persistencia.dao.exceptions.DaoException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementanción de la interface de la logica de negocio empleada para los
 * usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
@Service("com.irs.springbootstrapvalidationwebapp.negocio.servicios.UsuarioService")
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    /**
     * Objeto de persistencia de usuario.
     */
    @Autowired
    private UsuarioDao usuarioDao;

    /**
     * Constructor por defecto.
     */
    public UsuarioServiceImpl() {
        super();
    }

    /**
     * Busca usuarios por filtro.
     *
     * @param filterUsuario Filtro empleado en la busqueda.
     * @return Una lista con los usuarios que cumplan el filtro de busqueda.
     * @throws ServiceException Si se produce un error en la busqueda.
     */
    public List<UsuarioVO> findByFilter(FilterUsuarioVO filterUsuario)
            throws ServiceException {
         List<UsuarioVO> result = null;

        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Buscando usuarios por filtro '{}'", filterUsuario);
            }
            result = usuarioDao.selectByFilter(filterUsuario);
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(e);
        }

        return result;
    }

    /**
     * Busca un usuario por identificador.
     *
     * @param idUsuario identificador del usuario a buscar.
     * @return El usuario con el identificador especificado.
     * @throws ServiceException Si se produce un error en la busqueda.
     */
    public UsuarioVO findById(final Integer idUsuario) throws ServiceException {
        UsuarioVO result = null;

        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Buscando usuario por id '{}'", idUsuario);
            }
            result = usuarioDao.selectById(idUsuario);
        } catch (DaoException e) {
            LOG.error(e.getMessage());
            throw new ServiceException(e);
        }

        return result;
    }

    /**
     * Guarda un nuevo usuario.
     *
     * @param usuario el nuevo usuario a guardar.
     * @return El nuevo usuario guardado.
     * @throws ServiceException Si se produce un error al guardar el usuario.
     */
    public UsuarioVO save(UsuarioVO usuario) throws ServiceException {
        UsuarioVO result = null;

        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Guardando usuario");
            }

            // Establezco la fecha de alta a la fecha actual
            //usuario.setFechaAlta(new Date());

            result = usuarioDao.insert(usuario);
        } catch (DaoException e) {
            LOG.error(e.getMessage());
            throw new ServiceException(e);
        }

        return result;
    }

    /**
     * Actualiza un usuario.
     *
     * @param usuario el usuario a actualizar.
     * @throws ServiceException Si se produce un error en la actualizacion del
     * usuario.
     */
    public void update(UsuarioVO usuario) throws ServiceException {
        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Actualizando usuario");
            }

            usuarioDao.update(usuario);
        } catch (DaoException e) {
            LOG.error(e.getMessage());
            throw new ServiceException(e);
        }
    }

    /**
     * Borra un usuario (Borrado lógico).
     *
     * @param idUsuario identificador del usuario a borrar.
     * @throws ServiceException Si se produce un error en el borrado del
     * usuario.
     */
    public void remove(final Integer idUsuario) throws ServiceException {
        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Eliminando usuario por id '{}'", idUsuario);
            }

            usuarioDao.delete(usuarioDao.selectById(idUsuario));
        } catch (DaoException e) {
            LOG.error(e.getMessage());
            throw new ServiceException(e);
        }
    }
}
