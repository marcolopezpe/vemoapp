package pe.marcolopez.sistemas.vemoapp.service.venta.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.marcolopez.sistemas.vemoapp.dto.venta.ArticuloDTO;
import pe.marcolopez.sistemas.vemoapp.entity.venta.ArticuloEntity;
import pe.marcolopez.sistemas.vemoapp.repository.venta.ArticuloRepository;
import pe.marcolopez.sistemas.vemoapp.service.exception.ServiceException;
import pe.marcolopez.sistemas.vemoapp.service.venta.inf.ArticuloService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ArticuloServiceImpl implements ArticuloService {

    private final ArticuloRepository articuloRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ArticuloServiceImpl(ArticuloRepository articuloRepository, ObjectMapper objectMapper) {
        this.articuloRepository = articuloRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<ArticuloDTO> getAll() throws ServiceException {
        return getArticulosDTO(articuloRepository.findAllActivos());
    }

    @Override
    public ArticuloDTO findById(Long id) throws ServiceException {
        return getArticuloDTO(articuloRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public ArticuloDTO insert(ArticuloDTO articuloDTO) throws ServiceException {
        ArticuloEntity articuloEntity = objectMapper.convertValue(articuloDTO, ArticuloEntity.class);
        return getArticuloDTO(articuloRepository.save(articuloEntity));
    }

    @Override
    @Transactional
    public ArticuloDTO update(ArticuloDTO articuloDTO) throws ServiceException {
        ArticuloEntity articuloEntity = articuloRepository.findById(articuloDTO.getId()).orElse(null);
        assert articuloEntity != null;
        BeanUtils.copyProperties(articuloDTO, articuloEntity);
        return getArticuloDTO(articuloRepository.save(articuloEntity));
    }

    @Override
    @Transactional
    public ArticuloDTO delete(Long id) throws ServiceException {
        if (articuloRepository.findById(id).isPresent()) {
            ArticuloEntity articuloEntity = articuloRepository.findById(id).get();
            articuloEntity.setEstado(0);
            return getArticuloDTO(articuloRepository.save(articuloEntity));
        }
        return null;
    }

    private ArticuloDTO getArticuloDTO(ArticuloEntity articuloEntity) {
        return objectMapper.convertValue(articuloEntity, ArticuloDTO.class);
    }

    private List<ArticuloDTO> getArticulosDTO(List<ArticuloEntity> articulosEntity) {
        List<ArticuloDTO> articulosDTO = new ArrayList<>();
        articulosEntity.forEach(a -> articulosDTO.add(getArticuloDTO(a)));
        return articulosDTO;
    }
}