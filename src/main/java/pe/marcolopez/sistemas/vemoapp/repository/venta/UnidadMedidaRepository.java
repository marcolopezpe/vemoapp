package pe.marcolopez.sistemas.vemoapp.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.marcolopez.sistemas.vemoapp.entity.venta.UnidadMedidaEntity;

import java.util.List;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedidaEntity, Long> {

    @Query("select um from UnidadMedida um where um.estado=1")
    List<UnidadMedidaEntity> findAllActivos();
}
