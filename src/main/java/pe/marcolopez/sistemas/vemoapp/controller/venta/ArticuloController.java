package pe.marcolopez.sistemas.vemoapp.controller.venta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.marcolopez.sistemas.vemoapp.controller.commons.ResponseAPI;
import pe.marcolopez.sistemas.vemoapp.controller.generic.GenericController;
import pe.marcolopez.sistemas.vemoapp.dto.venta.ArticuloDTO;
import pe.marcolopez.sistemas.vemoapp.dto.venta.ArticuloStockDTO;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.ArticuloService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("articulos/v1")
public class ArticuloController extends GenericController {

    private final ArticuloService articuloService;

    @Autowired
    public ArticuloController(ArticuloService articuloService) {
        this.articuloService = articuloService;
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> getArticulos() {
        try {
            List<ArticuloDTO> articulosDTO = articuloService.getAll();
            if (articulosDTO.isEmpty()) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(articulosDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @GetMapping("final-stocks")
    public  ResponseEntity<ResponseAPI> getMovimientosByFinalStocks() {
        try {
            List<ArticuloStockDTO> articulosStockDTO = articuloService.getByFinalStocks();
            if (articulosStockDTO.isEmpty()) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(articulosStockDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseAPI> getArticulo(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return getBadIDRequest();
            }
            ArticuloDTO articuloDTO = articuloService.findById(id);
            if (articuloDTO == null) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(articuloDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseAPI> insert(@Validated @RequestBody ArticuloDTO articuloDTO,
                                              BindingResult result) {
        if (result.hasErrors()) return getBadRequest(result);

        try {
            ArticuloDTO articuloCreated = articuloService.insert(articuloDTO);
            if (articuloCreated != null) {
                return getCreatedRequest(articuloCreated);
            }
            return getErrorRequest();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseAPI> update(@PathVariable Long id,
                                              @Validated @RequestBody ArticuloDTO articuloDTO,
                                              BindingResult result) {
        if (id <= 0) return getBadIDRequest();

        articuloDTO.setId(id);
        if (result.hasErrors()) return getBadRequest(result);

        try {
            ArticuloDTO articuloUpdated = articuloService.update(articuloDTO);
            if (articuloUpdated != null) {
                return getOkRegiterRequest(articuloUpdated);
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
            ArticuloDTO articuloDeleted = articuloService.delete(id);
            if (articuloDeleted != null) {
                return getOkRegiterRequest(articuloDeleted);
            }
            return getErrorRequest();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }
}
