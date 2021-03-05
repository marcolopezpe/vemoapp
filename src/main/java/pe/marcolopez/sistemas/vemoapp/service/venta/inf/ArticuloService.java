package pe.marcolopez.sistemas.vemoapp.service.venta.inf;

import pe.marcolopez.sistemas.vemoapp.dto.venta.ArticuloDTO;
import pe.marcolopez.sistemas.vemoapp.dto.venta.ArticuloStockDTO;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.generic.GenericService;

import java.util.List;

public interface ArticuloService extends GenericService<ArticuloDTO> {

    List<ArticuloStockDTO> getByFinalStocks() throws ServiceException;
}
