package pe.marcolopez.sistemas.vemoapp.service.venta.inf;

import pe.marcolopez.sistemas.vemoapp.dto.venta.ComprobanteDTO;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.generic.GenericService;

import java.util.Date;
import java.util.List;

public interface ComprobanteService extends GenericService<ComprobanteDTO> {

    List<ComprobanteDTO> getComprobantesByFiltro(Date desde, Date hasta, String numero, String cliente) throws ServiceException;
}
