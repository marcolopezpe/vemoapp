package pe.marcolopez.sistemas.vemoapp.controller.venta;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pe.marcolopez.sistemas.vemoapp.controller.commons.ResponseAPI;
import pe.marcolopez.sistemas.vemoapp.controller.generic.GenericController;
import pe.marcolopez.sistemas.vemoapp.dto.venta.ClienteDTO;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.ClienteService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("clientes/v1")
public class ClienteController extends GenericController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<ResponseAPI> getClientes() {
        try {
            List<ClienteDTO> clientesDTO = clienteService.getAll();
            if (clientesDTO.isEmpty()) {
                return getNotFoundRequest();
            }
            return super.getSuccessRequest(clientesDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return super.getErrorRequest();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseAPI> getCliente(@PathVariable Long id) {
        try {
            if (id <= 0) {
                return getBadIDRequest();
            }
            ClienteDTO clienteDTO = clienteService.findById(id);
            if (clienteDTO == null) {
                return getNotFoundRequest();
            }
            return getSuccessRequest(clienteDTO);
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @PostMapping
    public ResponseEntity<ResponseAPI> insert(@Validated @RequestBody ClienteDTO clienteDTO,
                                              BindingResult result) {
        if (result.hasErrors()) return getBadRequest(result);

        try {
            ClienteDTO clienteCreated = clienteService.insert(clienteDTO);
            if (clienteCreated != null) {
                return getCreatedRequest(clienteCreated);
            }
            return getErrorRequest();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseAPI> update(@PathVariable Long id,
                                              @Validated @RequestBody ClienteDTO clienteDTO,
                                              BindingResult result) {
        if (id <= 0) return getBadIDRequest();

        clienteDTO.setId(id);
        if (result.hasErrors()) return getBadRequest(result);

        try {
            ClienteDTO clienteUpdated = clienteService.update(clienteDTO);
            if (clienteUpdated != null) {
                return getOkRegiterRequest(clienteUpdated);
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
            ClienteDTO clienteDeleted = clienteService.delete(id);
            if (clienteDeleted != null) {
                return getOkRegiterRequest(clienteDeleted);
            }
            return getErrorRequest();
        } catch (ServiceException e) {
            log.error(e.getMessage());
            return getErrorRequest();
        }
    }
}
