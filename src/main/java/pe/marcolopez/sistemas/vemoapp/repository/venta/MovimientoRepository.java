package pe.marcolopez.sistemas.vemoapp.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.marcolopez.sistemas.vemoapp.entity.venta.MovimientoEntity;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {

    @Query("select m from Movimiento m where m.estado=1")
    List<MovimientoEntity> findAllActivos();
}
