package pe.marcolopez.sistemas.vemoapp.service.venta.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.marcolopez.sistemas.vemoapp.dto.venta.UnidadMedidaDTO;
import pe.marcolopez.sistemas.vemoapp.entity.venta.UnidadMedidaEntity;
import pe.marcolopez.sistemas.vemoapp.repository.venta.UnidadMedidaRepository;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.UnidadMedidaService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UnidadMedidaServiceImpl implements UnidadMedidaService {

    private final UnidadMedidaRepository unidadMedidaRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UnidadMedidaServiceImpl(UnidadMedidaRepository unidadMedidaRepository, ObjectMapper objectMapper) {
        this.unidadMedidaRepository = unidadMedidaRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<UnidadMedidaDTO> getAll() throws ServiceException {
        return getUnidadMedidasDTO(unidadMedidaRepository.findAllActivos());
    }

    @Override
    public UnidadMedidaDTO findById(Long id) throws ServiceException {
        return getUnidadMedidaDTO(unidadMedidaRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public UnidadMedidaDTO insert(UnidadMedidaDTO unidadMedidaDTO) throws ServiceException {
        UnidadMedidaEntity unidadMedidaEntity = objectMapper.convertValue(unidadMedidaDTO, UnidadMedidaEntity.class);
        return getUnidadMedidaDTO(unidadMedidaRepository.save(unidadMedidaEntity));
    }

    @Override
    @Transactional
    public UnidadMedidaDTO update(UnidadMedidaDTO unidadMedidaDTO) throws ServiceException {
        UnidadMedidaEntity unidadMedidaEntity = unidadMedidaRepository.findById(unidadMedidaDTO.getId()).orElse(null);
        assert unidadMedidaEntity != null;
        BeanUtils.copyProperties(unidadMedidaDTO, unidadMedidaEntity);
        return getUnidadMedidaDTO(unidadMedidaRepository.save(unidadMedidaEntity));
    }

    @Override
    @Transactional
    public UnidadMedidaDTO delete(Long id) throws ServiceException {
        if (unidadMedidaRepository.findById(id).isPresent()) {
            UnidadMedidaEntity unidadMedidaEntity = unidadMedidaRepository.findById(id).get();
            unidadMedidaEntity.setEstado(0);
            return getUnidadMedidaDTO(unidadMedidaRepository.save(unidadMedidaEntity));
        }
        return null;
    }

    private UnidadMedidaDTO getUnidadMedidaDTO(UnidadMedidaEntity unidadMedidaEntity) {
        return objectMapper.convertValue(unidadMedidaEntity, UnidadMedidaDTO.class);
    }

    private List<UnidadMedidaDTO> getUnidadMedidasDTO(List<UnidadMedidaEntity> unidadMedidasEntity) {
        List<UnidadMedidaDTO> unidadMedidasDTO = new ArrayList<>();
        unidadMedidasEntity.forEach(um -> unidadMedidasDTO.add(getUnidadMedidaDTO(um)));
        return unidadMedidasDTO;
    }
}
