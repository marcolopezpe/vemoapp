package pe.marcolopez.sistemas.vemoapp.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.marcolopez.sistemas.vemoapp.entity.venta.ComprobanteDetalleEntity;

import java.util.List;

@Repository
public interface ComprobanteDetalleRepository extends JpaRepository<ComprobanteDetalleEntity, Long> {

    @Query("select cd from ComprobanteDetalle cd where cd.estado=1 and cd.comprobante.id=:comprobanteId")
    List<ComprobanteDetalleEntity> findAllByComprobanteId(Integer comprobanteId);
}
