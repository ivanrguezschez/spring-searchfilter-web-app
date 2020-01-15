package com.irs.springsearchfilterwebapp.persistencia.dao.hibernate;

import com.irs.springsearchfilterwebapp.dominio.TipoEntidad;
import com.irs.springsearchfilterwebapp.negocio.vo.TipoEntidadVO;
import com.irs.springsearchfilterwebapp.persistencia.dao.TipoEntidadDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Implementacion basada en Hibernate de la interface de la capa de persistencia
 * empleada para los tipos de entidad.
 *
 * @author IRS
 * @version 1.0.0
 */
@Repository
public class HibernateTipoEntidadDao implements TipoEntidadDao {

    @Autowired
    protected SessionFactory sessionFactory;

    @Autowired
    protected DozerBeanMapper mapperPersistencia;

    /**
     * Constructor por defecto.
     */
    public HibernateTipoEntidadDao() {
        super();
    }

    public List<TipoEntidadVO> selectAll() {
        List<TipoEntidadVO> result = null;

        result = toVo((List<TipoEntidad>) sessionFactory.getCurrentSession().createQuery("from TipoEntidad").list());

        return result;
    }

    private TipoEntidadVO toVo(TipoEntidad entity) {
        if (entity != null) {
            return (TipoEntidadVO) mapperPersistencia.map(entity, TipoEntidadVO.class);
        }

        return null;
    }

    private List<TipoEntidadVO> toVo(List<TipoEntidad> entities) {
        List<TipoEntidadVO> vos = null;

        if (entities != null) {
            vos = new ArrayList<TipoEntidadVO>();
            for (TipoEntidad entity : entities) {
                vos.add(toVo(entity));
            }
        }

        return vos;
    }

    private TipoEntidad toEntity(TipoEntidadVO vo) {
        if (vo != null) {
            return (TipoEntidad) mapperPersistencia.map(vo, TipoEntidad.class);
        }

        return null;
    }

    private List<TipoEntidad> toEntity(List<TipoEntidadVO> vos) {
        List<TipoEntidad> entities = null;

        if (vos != null) {
            entities = new ArrayList<TipoEntidad>();
            for (TipoEntidadVO vo : vos) {
                entities.add(toEntity(vo));
            }
        }

        return entities;
    }
}
