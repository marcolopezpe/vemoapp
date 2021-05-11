package pe.marcolopez.sistemas.vemoapp.controller.venta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.marcolopez.sistemas.vemoapp.controller.commons.ResponseAPI;
import pe.marcolopez.sistemas.vemoapp.controller.generic.GenericController;
import pe.marcolopez.sistemas.vemoapp.dto.venta.MovimientoDTO;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.MovimientoService;
import pe.marcolopez.sistemas.vemoapp.util.Util;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("movimientos/v1")
public class MovimientoController extends GenericController {

    private final MovimientoService movimientoService;

    @Autowired
    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> getMovimientos() {
        try {
            List<MovimientoDTO> movimientosDTO = movimientoService.getAll();
            if (movimientosDTO.isEmpty()) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(movimientosDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @GetMapping("filtro")
    public ResponseEntity<ResponseAPI> getMovimientosByFiltro(@RequestParam Long desde,
                                                              @RequestParam Long hasta,
                                                              @RequestParam String descripcion,
                                                              @RequestParam String tipo) {
        try {
            List<MovimientoDTO> movimientosDTO = movimientoService.getMovimientosByFiltro(
                    Util.extractDate(desde),
                    Util.extractDate(hasta),
                    descripcion,
                    tipo
            );

            if (movimientosDTO.isEmpty()) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(movimientosDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseAPI> getMovimiento(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return getBadIDRequest();
            }
            MovimientoDTO movimientoDTO = movimientoService.findById(id);
            if (movimientoDTO == null) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(movimientoDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseAPI> insert(@Validated @RequestBody MovimientoDTO movimientoDTO,
                                              BindingResult result) {
        if (result.hasErrors()) return getBadRequest(result);

        try {
            MovimientoDTO movimientoCreated = movimientoService.insert(movimientoDTO);
            if (movimientoCreated != null) {
                return getCreatedRequest(movimientoCreated);
            }
            return getErrorRequest();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseAPI> update(@PathVariable Long id,
                                              @Validated @RequestBody MovimientoDTO movimientoDTO,
                                              BindingResult result) {
        if (id <= 0) return getBadIDRequest();

        movimientoDTO.setId(id);
        if (result.hasErrors()) return getBadRequest(result);

        try {
            MovimientoDTO movimientoUpdated = movimientoService.update(movimientoDTO);
            if (movimientoUpdated != null) {
                return getOkRegiterRequest(movimientoUpdated);
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
            MovimientoDTO movimientoDeleted = movimientoService.delete(id);
            if (movimientoDeleted != null) {
                return getOkRegiterRequest(movimientoDeleted);
            }
            return getErrorRequest();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }
}
