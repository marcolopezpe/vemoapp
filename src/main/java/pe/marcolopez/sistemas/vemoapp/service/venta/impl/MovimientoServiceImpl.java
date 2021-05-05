package pe.marcolopez.sistemas.vemoapp.service.venta.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.marcolopez.sistemas.vemoapp.dto.venta.MovimientoDTO;
import pe.marcolopez.sistemas.vemoapp.entity.venta.ArticuloEntity;
import pe.marcolopez.sistemas.vemoapp.entity.venta.MovimientoEntity;
import pe.marcolopez.sistemas.vemoapp.repository.venta.ArticuloRepository;
import pe.marcolopez.sistemas.vemoapp.repository.venta.MovimientoRepository;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.MovimientoService;
import pe.marcolopez.sistemas.vemoapp.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class MovimientoServiceImpl implements MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ArticuloRepository articuloRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public MovimientoServiceImpl(MovimientoRepository movimientoRepository, ArticuloRepository articuloRepository, ObjectMapper objectMapper) {
        this.movimientoRepository = movimientoRepository;
        this.articuloRepository = articuloRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<MovimientoDTO> getAll() throws ServiceException {
        return getMovimientosDTO(movimientoRepository.findAllActivos());
    }

    @Override
    public List<MovimientoDTO> getMovimientosByFiltro(Date desde, Date hasta, String descripcion, String tipo) {
        return getMovimientosDTO(movimientoRepository.findAllByFiltro(desde, hasta, descripcion, tipo));
    }

    @Override
    public MovimientoDTO findById(Long id) throws ServiceException {
        return getMovimientoDTO(movimientoRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public MovimientoDTO insert(MovimientoDTO movimientoDTO) throws ServiceException {
        MovimientoEntity movimientoEntity = objectMapper.convertValue(movimientoDTO, MovimientoEntity.class);
        return getMovimientoDTO(movimientoRepository.save(movimientoEntity));
    }

    @Override
    @Transactional
    public MovimientoDTO update(MovimientoDTO movimientoDTO) throws ServiceException {
        MovimientoEntity movimientoEntity = movimientoRepository.findById(movimientoDTO.getId()).orElse(null);
        assert movimientoEntity != null;
        Util.copyProperties(movimientoDTO, movimientoEntity);

        ArticuloEntity articuloEntity = new ArticuloEntity();
        Util.copyProperties(movimientoDTO.getArticulo(), articuloEntity);
        movimientoEntity.setArticulo(articuloEntity);

        return getMovimientoDTO(movimientoRepository.save(movimientoEntity));
    }

    @Override
    @Transactional
    public MovimientoDTO delete(Long id) throws ServiceException {
        if (movimientoRepository.findById(id).isPresent()) {
            MovimientoEntity movimientoEntity = movimientoRepository.findById(id).get();
            movimientoEntity.setEstado(0);
            return getMovimientoDTO(movimientoRepository.save(movimientoEntity));
        }
        return null;
    }

    private MovimientoDTO getMovimientoDTO(MovimientoEntity movimientoEntity) {
        return objectMapper.convertValue(movimientoEntity, MovimientoDTO.class);
    }

    private List<MovimientoDTO> getMovimientosDTO(List<MovimientoEntity> movimientosEntity) {
        List<MovimientoDTO> movimientosDTO = new ArrayList<>();
        movimientosEntity.forEach(m -> movimientosDTO.add(getMovimientoDTO(m)));
        return movimientosDTO;
    }
}
