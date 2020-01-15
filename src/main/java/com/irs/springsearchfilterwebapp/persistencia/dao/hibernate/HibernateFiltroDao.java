package com.irs.springsearchfilterwebapp.persistencia.dao.hibernate;

import com.irs.springsearchfilterwebapp.dominio.Filtro;
import com.irs.springsearchfilterwebapp.negocio.vo.FiltroVO;
import com.irs.springsearchfilterwebapp.persistencia.dao.FiltroDao;
import com.irs.springsearchfilterwebapp.persistencia.dao.exceptions.DaoException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementacion basada en Hibernate de la interface de la capa de persistencia
 * empleada para los filtros.
 *
 * @author IRS
 * @version 1.0.0
 */
@Repository
public class HibernateFiltroDao implements FiltroDao {

    private static final Logger LOG = LoggerFactory.getLogger(HibernateFiltroDao.class);

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected DozerBeanMapper mapperPersistencia;
  
    /**
     * Constructor por defecto.
     */
    public HibernateFiltroDao() {
        super();
    }
    
    /*
    public List<FiltroVO> selectByUsuario(UsuarioVO usuario) 
            throws DaoException {
        List<FiltroVO> result = null;

        try {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Buscando filtros por usuario: {}", usuario);
            }

            Criteria criteria = buildCriteria(usuario);
            result = toVo((List<Filtro>) criteria.list());

            if (LOG.isDebugEnabled()) {
                LOG.debug("Numero de filtros encontrados: " + (result == null ? 0 : result.size()));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            throw new DaoException("Error buscando filtros por usuario '" + usuario + "': " + e.getMessage(), e);
        }

        return result;
    }
    */
    public List<FiltroVO> selectAll() throws DaoException {
         List<FiltroVO> result = null;

        result = toVo((List<Filtro>) sessionFactory.getCurrentSession().createQuery("from Filtro").list());

        return result;
    }

    public FiltroVO selectById(Integer idFiltro) throws DaoException {
        if (idFiltro == null) {
            throw new DaoException("Identificador de filtro nulo");
        }

        try {
            LOG.debug("Seleccionando filtro por id '{}'", idFiltro);
            Filtro entity = (Filtro) sessionFactory.getCurrentSession().get(Filtro.class, idFiltro);
            sessionFactory.getCurrentSession().clear();
            
            return toVo(entity);
        } catch (Exception e) {
            throw new DaoException("Error buscando filtro por identificador (" + idFiltro + ")", e);
        }
    }

    public FiltroVO insert(FiltroVO filtro) throws DaoException {
        if (filtro == null) {
            throw new DaoException("Filtro nulo");
        }

        try {
            LOG.debug("Insertando filtro '{}'", filtro);
            Filtro entity = toEntity(filtro);
            sessionFactory.getCurrentSession().save(entity);
            
            return toVo(entity);
        } catch (Exception e) {
            throw new DaoException("Error insertando filtro", e);
        }
    }
        
    public void delete(FiltroVO filtro) throws DaoException {
        if (filtro == null) {
            throw new DaoException("Filtro nulo");
        }

        try {
            LOG.debug("Eliminando filtro '{}'", filtro);
            Filtro entity = toEntity(filtro);
            sessionFactory.getCurrentSession().delete(entity);
            sessionFactory.getCurrentSession().flush();
        } catch (Exception e) {
            throw new DaoException("Error eliminando filtro", e);
        }
    }
     
    /*
    private Criteria buildCriteria(UsuarioVO usuario) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Filtro.class);

        if (usuario != null) {
            criteria.add(Restrictions.eq("usuario.idUsuario", usuario.getIdUsuario()));
        }

        return criteria;
    }
    */
    
    private FiltroVO toVo(Filtro entity) {
        if (entity != null) {
            return (FiltroVO) mapperPersistencia.map(entity, FiltroVO.class);
        }

        return null;
    }

    private List<FiltroVO> toVo(List<Filtro> entities) {
        List<FiltroVO> vos = null;

        if (entities != null) {
            vos = new ArrayList<FiltroVO>();
            for (Filtro entity : entities) {
                vos.add(toVo(entity));
            }
        }

        return vos;
    }

    private Filtro toEntity(FiltroVO vo) {
        if (vo != null) {
            return (Filtro) mapperPersistencia.map(vo, Filtro.class);
        }

        return null;
    }

    private List<Filtro> toEntity(List<FiltroVO> vos) {
        List<Filtro> entities = null;

        if (vos != null) {
            entities = new ArrayList<Filtro>();
            for (FiltroVO vo : vos) {
                entities.add(toEntity(vo));
            }
        }

        return entities;
    }
}
