package pe.marcolopez.sistemas.vemoapp.service.venta.inf;

import pe.marcolopez.sistemas.vemoapp.dto.security.UsuarioDTO;
import pe.marcolopez.sistemas.vemoapp.service.generic.GenericService;

public interface UsuarioService extends GenericService<UsuarioDTO> {

    UsuarioDTO getUsuarioByUsuario(String usuario);
}
