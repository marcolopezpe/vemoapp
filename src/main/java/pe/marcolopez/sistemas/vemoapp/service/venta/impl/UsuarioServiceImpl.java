package pe.marcolopez.sistemas.vemoapp.service.venta.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.marcolopez.sistemas.vemoapp.dto.security.UsuarioDTO;
import pe.marcolopez.sistemas.vemoapp.entity.security.UsuarioEntity;
import pe.marcolopez.sistemas.vemoapp.repository.security.UsuarioRepository;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.UsuarioService;

import java.util.List;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, ObjectMapper objectMapper) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<UsuarioDTO> getAll() throws ServiceException {
        return null;
    }

    @Override
    public UsuarioDTO findById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public UsuarioDTO insert(UsuarioDTO usuarioDTO) throws ServiceException {
        return null;
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuarioDTO) throws ServiceException {
        return null;
    }

    @Override
    public UsuarioDTO delete(Long id) throws ServiceException {
        return null;
    }

    @Override
    public UsuarioDTO getUsuarioByUsuario(String usuario) {
        return getUsuarioDTO(usuarioRepository.loadUsuarioEntityByUsuario(usuario));
    }

    private UsuarioDTO getUsuarioDTO(UsuarioEntity usuarioEntity) {
        return objectMapper.convertValue(usuarioEntity, UsuarioDTO.class);
    }
}
