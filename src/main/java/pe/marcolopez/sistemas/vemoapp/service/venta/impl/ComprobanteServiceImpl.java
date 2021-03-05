package pe.marcolopez.sistemas.vemoapp.service.venta.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.marcolopez.sistemas.vemoapp.dto.venta.ComprobanteDTO;
import pe.marcolopez.sistemas.vemoapp.entity.venta.ComprobanteEntity;
import pe.marcolopez.sistemas.vemoapp.repository.venta.ComprobanteRepository;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.ComprobanteService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ComprobanteServiceImpl(ComprobanteRepository comprobanteRepository, ObjectMapper objectMapper) {
        this.comprobanteRepository = comprobanteRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<ComprobanteDTO> getAll() throws ServiceException {
        return getComprobantesDTO(comprobanteRepository.findAllActivos());
    }

    @Override
    public ComprobanteDTO findById(Long id) throws ServiceException {
        return getComprobanteDTO(comprobanteRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public ComprobanteDTO insert(ComprobanteDTO comprobanteDTO) throws ServiceException {
        ComprobanteEntity comprobanteEntity = objectMapper.convertValue(comprobanteDTO, ComprobanteEntity.class);
        comprobanteEntity.getDetalles().forEach(cd -> cd.setComprobante(comprobanteEntity));
        return getComprobanteDTO(comprobanteRepository.save(comprobanteEntity));
    }

    @Override
    @Transactional
    public ComprobanteDTO update(ComprobanteDTO comprobanteDTO) throws ServiceException {
        ComprobanteEntity comprobanteEntity = comprobanteRepository.findById(comprobanteDTO.getId()).orElse(null);
        assert comprobanteEntity != null;
        BeanUtils.copyProperties(comprobanteDTO, comprobanteEntity);
        return getComprobanteDTO(comprobanteRepository.save(comprobanteEntity));
    }

    @Override
    @Transactional
    public ComprobanteDTO delete(Long id) throws ServiceException {
        if (comprobanteRepository.findById(id).isPresent()) {
            ComprobanteEntity comprobanteEntity = comprobanteRepository.findById(id).get();
            comprobanteEntity.setEstado(0);
            return getComprobanteDTO(comprobanteRepository.save(comprobanteEntity));
        }
        return null;
    }

    private ComprobanteDTO getComprobanteDTO(ComprobanteEntity comprobanteEntity) {
        return objectMapper.convertValue(comprobanteEntity, ComprobanteDTO.class);
    }

    private List<ComprobanteDTO> getComprobantesDTO(List<ComprobanteEntity> comprobantesEntity) {
        List<ComprobanteDTO> comprobantesDTO = new ArrayList<>();
        comprobantesEntity.forEach(c -> comprobantesDTO.add(getComprobanteDTO(c)));
        return comprobantesDTO;
    }
}
