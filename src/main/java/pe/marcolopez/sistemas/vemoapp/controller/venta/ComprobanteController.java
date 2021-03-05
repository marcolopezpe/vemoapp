package pe.marcolopez.sistemas.vemoapp.controller.venta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.marcolopez.sistemas.vemoapp.controller.commons.ResponseAPI;
import pe.marcolopez.sistemas.vemoapp.controller.generic.GenericController;
import pe.marcolopez.sistemas.vemoapp.dto.venta.ComprobanteDTO;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.ComprobanteService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("comprobantes/v1")
public class ComprobanteController extends GenericController {

    private final ComprobanteService comprobanteService;

    @Autowired
    public ComprobanteController(ComprobanteService comprobanteService) {
        this.comprobanteService = comprobanteService;
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> getComprobantes() {
        try {
            List<ComprobanteDTO> comprobantesDTO = comprobanteService.getAll();
            if (comprobantesDTO.isEmpty()) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(comprobantesDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseAPI> getComprobante(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return getBadIDRequest();
            }
            ComprobanteDTO comprobanteDTO = comprobanteService.findById(id);
            if (comprobanteDTO == null) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(comprobanteDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseAPI> insert(@Validated @RequestBody ComprobanteDTO comprobanteDTO,
                                              BindingResult result) {
        if (result.hasErrors()) return getBadRequest(result);

        try {
            ComprobanteDTO comprobanteCreated = comprobanteService.insert(comprobanteDTO);
            if (comprobanteCreated != null) {
                return getCreatedRequest(comprobanteCreated);
            }
            return getErrorRequest();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseAPI> delete(@PathVariable Long id) {
        if (id <= 0) return ResponseEntity.badRequest().build();

        try {
            ComprobanteDTO comprobanteDeleted = comprobanteService.delete(id);
            if (comprobanteDeleted != null) {
                return getOkRegiterRequest(comprobanteDeleted);
            }
            return getErrorRequest();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }
}
