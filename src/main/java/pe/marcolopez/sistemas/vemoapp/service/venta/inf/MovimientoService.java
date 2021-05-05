package pe.marcolopez.sistemas.vemoapp.service.venta.inf;

import pe.marcolopez.sistemas.vemoapp.dto.venta.MovimientoDTO;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.generic.GenericService;

import java.util.Date;
import java.util.List;

public interface MovimientoService extends GenericService<MovimientoDTO> {

    List<MovimientoDTO> getMovimientosByFiltro(Date desde, Date hasta, String descripcion, String tipo) throws ServiceException;
}
