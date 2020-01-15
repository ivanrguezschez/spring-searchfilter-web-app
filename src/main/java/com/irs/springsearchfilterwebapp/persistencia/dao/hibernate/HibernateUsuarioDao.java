package com.irs.springsearchfilterwebapp.persistencia.dao.hibernate;

import com.irs.springsearchfilterwebapp.dominio.Usuario;
import com.irs.springsearchfilterwebapp.negocio.vo.FilterUsuarioVO;
import com.irs.springsearchfilterwebapp.negocio.vo.UsuarioVO;
import com.irs.springsearchfilterwebapp.persistencia.dao.UsuarioDao;
import com.irs.springsearchfilterwebapp.persistencia.dao.exceptions.DaoException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementacion basada en Hibernate de la interface de la capa de persistencia
 * empleada para los usuarios.
 *
 * @author IRS
 * @version 1.0.0
 */
@Repository
public class HibernateUsuarioDao implements UsuarioDao {

    private static final Logger LOG = LoggerFactory.getLogger(HibernateUsuarioDao.class);

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected DozerBeanMapper mapperPersistencia;
  
    /**
     * Constructor por defecto.
     */
    public HibernateUsuarioDao() {
        super();
    }

    public List<UsuarioVO> selectByFilter(FilterUsuarioVO filterUsuario) 
            throws DaoException {
        List<UsuarioVO> result = null;

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Buscando usuarios por filtro: " + filterUsuario);
            }

            Criteria criteria = buildCriteria(filterUsuario);
            result = toVo((List<Usuario>) criteria.list());

            if (LOG.isDebugEnabled()) {
                LOG.debug("Numero de usuarios encontrados: " + (result == null ? 0 : result.size()));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new DaoException("Error buscando usuarios por filtro '" + filterUsuario + "': " + e.getMessage(), e);
        }

        return result;
    }

    public UsuarioVO selectById(Integer idUsuario) throws DaoException {
        if (idUsuario == null) {
            throw new DaoException("Identificador de usuario nulo");
        }

        try {
            LOG.debug("Seleccionando usuario por id '{}'", idUsuario);
            Usuario entity = (Usuario) sessionFactory.getCurrentSession().get(Usuario.class, idUsuario);
            sessionFactory.getCurrentSession().clear();
            
            return toVo(entity);
        } catch (Exception e) {
            throw new DaoException("Error buscando usuario por identificador (" + idUsuario + ")", e);
        }
    }

    public UsuarioVO insert(UsuarioVO usuario) throws DaoException {
        if (usuario == null) {
            throw new DaoException("Usuario nulo");
        }

        try {
            LOG.debug("Insertando usuario '{}'", usuario);
            Usuario entity = toEntity(usuario);
            sessionFactory.getCurrentSession().save(entity);
            
            return toVo(entity);
        } catch (Exception e) {
            throw new DaoException("Error insertando usuario", e);
        }
    }
    
    public void update(UsuarioVO usuario) throws DaoException {
        if (usuario == null) {
            throw new DaoException("Usuario nulo");
        }

        try {
            LOG.debug("Actualizando usuario '{}'", usuario);
            Usuario entity = toEntity(usuario);
            sessionFactory.getCurrentSession().saveOrUpdate(entity);
        } catch (Exception e) {
            throw new DaoException("Error actualizando usuario", e);
        }
    }
     
    public void delete(UsuarioVO usuario) throws DaoException {
        if (usuario == null) {
            throw new DaoException("Usuario nulo");
        }

        try {
            LOG.debug("Eliminando usuario '{}'", usuario);
            Usuario entity = toEntity(usuario);
            sessionFactory.getCurrentSession().delete(entity);
            sessionFactory.getCurrentSession().flush();
        } catch (Exception e) {
            throw new DaoException("Error eliminando usuario", e);
        }
    }
     
    private Criteria buildCriteria(FilterUsuarioVO filterUsuario) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Usuario.class);

        if (filterUsuario != null) {
            if (StringUtils.isNotEmpty(filterUsuario.getNif())) {
                criteria.add(Restrictions.ilike("nif", filterUsuario.getNif(), MatchMode.ANYWHERE));
            }
            if (StringUtils.isNotEmpty(filterUsuario.getNombre())) {
                criteria.add(Restrictions.ilike("nombre", filterUsuario.getNombre(), MatchMode.ANYWHERE));
            }
            if (StringUtils.isNotEmpty(filterUsuario.getApellido1())) {
                criteria.add(Restrictions.ilike("apellido1", filterUsuario.getApellido1(), MatchMode.ANYWHERE));
            }
            if (StringUtils.isNotEmpty(filterUsuario.getApellido2())) {
                criteria.add(Restrictions.ilike("apellido2", filterUsuario.getApellido2(), MatchMode.ANYWHERE));
            }
            if (filterUsuario.getRol() != null) {
                criteria.add(Restrictions.eq("rol.idRol", filterUsuario.getRol().getIdRol()));
            }
          
            // Comentado ya que no funciona con HSQL
            //criteria.addOrder(Order.asc(Const.FN_NIF));
            //criteria.addOrder(Order.asc(Const.FN_NOMBRE));
            //criteria.addOrder(Order.asc(Const.FN_APELLIDO1));
            //criteria.addOrder(Order.asc(Const.FN_APELLIDO2));
        }

        return criteria;
    }
    
     private UsuarioVO toVo(Usuario entity) {
        if (entity != null) {
            return (UsuarioVO) mapperPersistencia.map(entity, UsuarioVO.class);
        }

        return null;
    }

    private List<UsuarioVO> toVo(List<Usuario> entities) {
        List<UsuarioVO> vos = null;

        if (entities != null) {
            vos = new ArrayList<UsuarioVO>();
            for (Usuario entity : entities) {
                vos.add(toVo(entity));
            }
        }

        return vos;
    }

    private Usuario toEntity(UsuarioVO vo) {
        if (vo != null) {
            return (Usuario) mapperPersistencia.map(vo, Usuario.class);
        }

        return null;
    }

    private List<Usuario> toEntity(List<UsuarioVO> vos) {
        List<Usuario> entities = null;

        if (vos != null) {
            entities = new ArrayList<Usuario>();
            for (UsuarioVO vo : vos) {
                entities.add(toEntity(vo));
            }
        }

        return entities;
    }
}
