package pe.marcolopez.sistemas.vemoapp.service.venta.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.marcolopez.sistemas.vemoapp.dto.venta.SerieComprobanteDTO;
import pe.marcolopez.sistemas.vemoapp.entity.venta.SerieComprobanteEntity;
import pe.marcolopez.sistemas.vemoapp.repository.venta.SerieComprobanteRepository;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.SerieComprobanteService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SerieComprobanteServiceImpl implements SerieComprobanteService {

    private final SerieComprobanteRepository serieComprobanteRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public SerieComprobanteServiceImpl(SerieComprobanteRepository serieComprobanteRepository, ObjectMapper objectMapper) {
        this.serieComprobanteRepository = serieComprobanteRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<SerieComprobanteDTO> getAll() throws ServiceException {
        return getSerieComprobantesDTO(serieComprobanteRepository.findAllActivos());
    }

    @Override
    public SerieComprobanteDTO findById(Long id) throws ServiceException {
        return null;
    }

    @Override
    public SerieComprobanteDTO insert(SerieComprobanteDTO serieComprobanteDTO) throws ServiceException {
        return null;
    }

    @Override
    public SerieComprobanteDTO update(SerieComprobanteDTO serieComprobanteDTO) throws ServiceException {
        return null;
    }

    @Override
    public SerieComprobanteDTO delete(Long id) throws ServiceException {
        return null;
    }

    private SerieComprobanteDTO getSerieComprobanteDTO(SerieComprobanteEntity serieComprobanteEntity) {
        return objectMapper.convertValue(serieComprobanteEntity, SerieComprobanteDTO.class);
    }

    private List<SerieComprobanteDTO> getSerieComprobantesDTO(List<SerieComprobanteEntity> serieComprobantesEntity) {
        List<SerieComprobanteDTO> serieComprobantesDTO = new ArrayList<>();
        serieComprobantesEntity.forEach(sc -> serieComprobantesDTO.add(getSerieComprobanteDTO(sc)));
        return serieComprobantesDTO;
    }
}
