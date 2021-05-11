package pe.marcolopez.sistemas.vemoapp.service.venta.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.marcolopez.sistemas.vemoapp.dto.venta.ComprobanteDTO;
import pe.marcolopez.sistemas.vemoapp.entity.venta.ComprobanteDetalleEntity;
import pe.marcolopez.sistemas.vemoapp.entity.venta.ComprobanteEntity;
import pe.marcolopez.sistemas.vemoapp.entity.venta.MovimientoEntity;
import pe.marcolopez.sistemas.vemoapp.repository.venta.ComprobanteDetalleRepository;
import pe.marcolopez.sistemas.vemoapp.repository.venta.ComprobanteRepository;
import pe.marcolopez.sistemas.vemoapp.repository.venta.MovimientoRepository;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.ComprobanteService;
import pe.marcolopez.sistemas.vemoapp.util.Util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ComprobanteServiceImpl implements ComprobanteService {

    private final ComprobanteRepository comprobanteRepository;
    private final ComprobanteDetalleRepository comprobanteDetalleRepository;
    private final MovimientoRepository movimientoRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ComprobanteServiceImpl(ComprobanteRepository comprobanteRepository, ComprobanteDetalleRepository comprobanteDetalleRepository, MovimientoRepository movimientoRepository, ObjectMapper objectMapper) {
        this.comprobanteRepository = comprobanteRepository;
        this.comprobanteDetalleRepository = comprobanteDetalleRepository;
        this.movimientoRepository = movimientoRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<ComprobanteDTO> getAll() throws ServiceException {
        return getComprobantesDTO(comprobanteRepository.findAllActivos());
    }

    @Override
    public List<ComprobanteDTO> getComprobantesByFiltro(Date desde, Date hasta, String numero, String cliente) {
        return getComprobantesDTO(comprobanteRepository.findAllByFiltro(desde, hasta, numero, cliente));
    }

    @Override
    public ComprobanteDTO findById(Long id) throws ServiceException {
        return getComprobanteDTO(comprobanteRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public ComprobanteDTO insert(ComprobanteDTO comprobanteDTO) throws ServiceException {
        ComprobanteEntity comprobanteEntity = objectMapper.convertValue(comprobanteDTO, ComprobanteEntity.class);
        comprobanteEntity.getDetalles().forEach(detalleEntity -> detalleEntity.setComprobante(comprobanteEntity));

        ComprobanteEntity comprobanteEntitySaved = comprobanteRepository.save(comprobanteEntity);

        return detalleEntityToSalida(comprobanteDTO, comprobanteEntitySaved);
    }

    @Override
    @Transactional
    public ComprobanteDTO update(ComprobanteDTO comprobanteDTO) throws ServiceException {
        ComprobanteEntity comprobanteEntity = comprobanteRepository.findById(comprobanteDTO.getId()).orElse(null);

        String comprobanteNumero = comprobanteDTO.getSerie() + "-" + comprobanteDTO.getNumero();
        movimientoRepository.deleteAllByComprobanteNumero(comprobanteNumero);
        comprobanteDetalleRepository.deleteAllByComprobanteId(comprobanteDTO.getId());

        Util.copyProperties(comprobanteDTO, comprobanteEntity);

        List<ComprobanteDetalleEntity> detallesEntities = new ArrayList<>();
        comprobanteDTO.getDetalles().forEach(detalleDTO -> {
            ComprobanteDetalleEntity detalleEntity = objectMapper.convertValue(detalleDTO, ComprobanteDetalleEntity.class);
            detalleEntity.setComprobante(comprobanteEntity);
            detallesEntities.add(detalleEntity);
        });

        assert comprobanteEntity != null;
        comprobanteEntity.setDetalles(detallesEntities);

        ComprobanteEntity comprobanteEntityUpdated = comprobanteRepository.save(comprobanteEntity);

        return detalleEntityToSalida(comprobanteDTO, comprobanteEntityUpdated);
    }

    @Override
    @Transactional
    public ComprobanteDTO delete(Long id) throws ServiceException {
        if (comprobanteRepository.findById(id).isPresent()) {
            ComprobanteEntity comprobanteEntity = comprobanteRepository.findById(id).get();
            comprobanteEntity.setEstado(0);
            ComprobanteEntity comprobanteEntitySaved = comprobanteRepository.save(comprobanteEntity);

            String comprobanteNumero = comprobanteEntity.getSerie() + "-" + comprobanteEntity.getNumero();
            movimientoRepository.updateEstadoByComprobanteNumero(comprobanteNumero);
            comprobanteDetalleRepository.updateEstadoByComprobanteId(comprobanteEntitySaved.getId());

            return getComprobanteDTO(comprobanteEntitySaved);
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

    private ComprobanteDTO detalleEntityToSalida(ComprobanteDTO comprobanteDTO, ComprobanteEntity comprobanteEntityUpdated) {
        comprobanteEntityUpdated.getDetalles().forEach(detalleEntity -> {
            MovimientoEntity movimientoEntity = new MovimientoEntity();
            movimientoEntity.setFecha(comprobanteDTO.getFecha());
            movimientoEntity.setArticulo(detalleEntity.getArticulo());
            movimientoEntity.setCantidad(detalleEntity.getCantidad());
            movimientoEntity.setKilos(detalleEntity.getKilos());
            movimientoEntity.setTipo("S");
            movimientoEntity.setEstado(1);
            movimientoEntity.setComprobanteNumero(comprobanteDTO.getSerie() + "-" + comprobanteDTO.getNumero());
            movimientoEntity.setDetalle(detalleEntity);

            movimientoRepository.save(movimientoEntity);
        });

        ComprobanteEntity comprobanteEntityFinal = comprobanteRepository.save(comprobanteEntityUpdated);
        return getComprobanteDTO(comprobanteEntityFinal);
    }
}
