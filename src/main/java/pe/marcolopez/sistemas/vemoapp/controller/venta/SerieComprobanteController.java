package pe.marcolopez.sistemas.vemoapp.controller.venta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.sistemas.vemoapp.controller.commons.ResponseAPI;
import pe.marcolopez.sistemas.vemoapp.controller.generic.GenericController;
import pe.marcolopez.sistemas.vemoapp.dto.venta.SerieComprobanteDTO;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.SerieComprobanteService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("series-comprobantes/v1")
public class SerieComprobanteController extends GenericController  {

    private final SerieComprobanteService serieComprobanteService;

    @Autowired
    public SerieComprobanteController(SerieComprobanteService serieComprobanteService) {
        this.serieComprobanteService = serieComprobanteService;
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> getSeriesComprobantes() {
        try {
            List<SerieComprobanteDTO> serieComprobantesDTO = serieComprobanteService.getAll();
            if (serieComprobantesDTO.isEmpty()) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(serieComprobantesDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }
}
