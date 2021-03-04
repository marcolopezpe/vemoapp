package pe.marcolopez.sistemas.vemoapp.service.venta.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.marcolopez.sistemas.vemoapp.dto.venta.ClienteDTO;
import pe.marcolopez.sistemas.vemoapp.entity.venta.ClienteEntity;
import pe.marcolopez.sistemas.vemoapp.repository.venta.ClienteRepository;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.ClienteService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;
    private final ObjectMapper objectMapper;

    public ClienteServiceImpl(ClienteRepository clienteRepository, ObjectMapper objectMapper) {
        this.clienteRepository = clienteRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<ClienteDTO> getAll() {
        return getClientesDTO(clienteRepository.findAllActivos());
    }

    @Override
    public ClienteDTO findById(Long id) throws ServiceException {
        return getClienteDTO(clienteRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public ClienteDTO insert(ClienteDTO clienteDTO) throws ServiceException {
        ClienteEntity clienteEntity = objectMapper.convertValue(clienteDTO, ClienteEntity.class);
        return getClienteDTO(clienteRepository.save(clienteEntity));
    }

    @Override
    @Transactional
    public ClienteDTO update(ClienteDTO clienteDTO) throws ServiceException {
        ClienteEntity clienteEntity = clienteRepository.findById(clienteDTO.getId()).orElse(null);
        assert clienteEntity != null;
        BeanUtils.copyProperties(clienteDTO, clienteEntity);
        return getClienteDTO(clienteRepository.save(clienteEntity));
    }

    @Override
    @Transactional
    public ClienteDTO delete(Long id) throws ServiceException {
        if (clienteRepository.findById(id).isPresent()) {
            ClienteEntity clienteEntity = clienteRepository.findById(id).get();
            clienteEntity.setEstado(0);
            return getClienteDTO(clienteRepository.save(clienteEntity));
        }
        return null;
    }

    private ClienteDTO getClienteDTO(ClienteEntity clienteEntity) {
        return objectMapper.convertValue(clienteEntity, ClienteDTO.class);
    }

    private List<ClienteDTO> getClientesDTO(List<ClienteEntity> clientesEntity) {
        List<ClienteDTO> clientesDTO = new ArrayList<>();
        clientesEntity.forEach(c -> clientesDTO.add(getClienteDTO(c)));
        return clientesDTO;
    }
}
