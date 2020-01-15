package com.irs.springsearchfilterwebapp.negocio.servicios.impl;

import com.irs.springsearchfilterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springsearchfilterwebapp.persistencia.dao.exceptions.DaoException;
import com.irs.springsearchfilterwebapp.negocio.servicios.TipoEntidadService;
import com.irs.springsearchfilterwebapp.negocio.vo.TipoEntidadVO;
import com.irs.springsearchfilterwebapp.persistencia.dao.TipoEntidadDao;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementanción de la interface de la logica de negocio empleada para los
 * tipos de entidad.
 *
 * @author IRS
 * @version 1.0.0
 */
@Service("com.irs.springbootstrapvalidationwebapp.negocio.servicios.TipoEntidadService")
public class TipoEntidadServiceImpl implements TipoEntidadService {

    private static final Logger LOG = LoggerFactory.getLogger(TipoEntidadServiceImpl.class);

    /**
     * Objeto de persistencia de tipo de entidad.
     */
    @Autowired
    private TipoEntidadDao tipoEntidadDao;

    /**
     * Constructor por defecto.
     */
    public TipoEntidadServiceImpl() {
        super();
    }

    public List<TipoEntidadVO> findAll() throws ServiceException {
         List<TipoEntidadVO> result = null;

        try {
            if (LOG.isInfoEnabled()) {
                LOG.info("Buscando todos los tipos de entidad");
            }
            result = tipoEntidadDao.selectAll();
        } catch (DaoException e) {
            LOG.error(e.getMessage(), e);
            throw new ServiceException(e);
        }

        return result;
    }
}
