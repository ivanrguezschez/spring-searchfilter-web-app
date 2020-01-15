package com.irs.springsearchfilterwebapp.negocio.servicios;

import com.irs.springsearchfilterwebapp.negocio.servicios.exceptions.ServiceException;
import com.irs.springsearchfilterwebapp.negocio.vo.TipoEntidadVO;
import java.util.List;

/**
 * Interface de la lógica de negocio empleada para los tipos de entidad.
 *
 * @author IRS
 * @version 1.0.0
 */
public interface TipoEntidadService {

   List<TipoEntidadVO> findAll() throws ServiceException;
}
