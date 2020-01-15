package com.irs.springsearchfilterwebapp.negocio.servicios;

import com.irs.springsearchfilterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springsearchfilterwebapp.negocio.vo.FilterUsuarioVO;
import com.irs.springsearchfilterwebapp.negocio.vo.UsuarioVO;
import java.util.List;

/**
 * Interface de la lógica de negocio empleada para los usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
public interface UsuarioService {

    /**
     * Busca usuarios por filtro.
     *
     * @param filterUsuario Filtro empleado en la busqueda.
     * @return Una lista con los usuarios que cumplan el filtro de busqueda.
     * @throws ServiceException Si se produce un error en la busqueda.
     */
    List<UsuarioVO> findByFilter(FilterUsuarioVO filterUsuario)
            throws ServiceException;

    /**
     * Busca un usuario por identificador.
     *
     * @param idUsuario identificador del usuario a buscar.
     * @return El usuario con el identificador especificado.
     * @throws ServiceException Si se produce un error en la busqueda.
     */
    UsuarioVO findById(final Integer idUsuario) throws ServiceException;

    /**
     * Guarda un nuevo usuario.
     *
     * @param usuario el nuevo usuario a guardar.
     * @return El nuevo usuario guardado.
     * @throws ServiceException Si se produce un error al guardar el usuario.
     */
    UsuarioVO save(final UsuarioVO usuario) throws ServiceException;

    /**
     * Actualiza un usuario.
     *
     * @param usuario El usuario a actualizar.
     * @throws ServiceException Si se produce un error en la actualizacion del
     * usuario.
     */
    void update(final UsuarioVO usuario) throws ServiceException;

    /**
     * Borra un usuario (Borrado lógico).
     *
     * @param idUsuario identificador del usuario a borrar.
     * @throws ServiceException Si se produce un error en el borrado del
     * usuario.
     */
    void remove(final Integer idUsuario) throws ServiceException;
}
