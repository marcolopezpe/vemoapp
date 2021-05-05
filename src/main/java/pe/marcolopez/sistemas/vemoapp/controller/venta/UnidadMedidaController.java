package pe.marcolopez.sistemas.vemoapp.controller.venta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.marcolopez.sistemas.vemoapp.controller.commons.ResponseAPI;
import pe.marcolopez.sistemas.vemoapp.controller.generic.GenericController;
import pe.marcolopez.sistemas.vemoapp.dto.venta.UnidadMedidaDTO;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.UnidadMedidaService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("unidades-medidas/v1")
public class UnidadMedidaController extends GenericController {

    private final UnidadMedidaService unidadMedidaService;

    @Autowired
    public UnidadMedidaController(UnidadMedidaService unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> getUnidadesMedidas() {
        try {
            List<UnidadMedidaDTO> unidadesMedidasDTO = unidadMedidaService.getAll();
            if (unidadesMedidasDTO.isEmpty()) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(unidadesMedidasDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }
}
