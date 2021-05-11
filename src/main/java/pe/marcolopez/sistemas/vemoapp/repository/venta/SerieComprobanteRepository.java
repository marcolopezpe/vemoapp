package pe.marcolopez.sistemas.vemoapp.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.marcolopez.sistemas.vemoapp.entity.venta.SerieComprobanteEntity;

import java.util.List;

@Repository
public interface SerieComprobanteRepository extends JpaRepository<SerieComprobanteEntity, Long> {

    @Query("select sc from SerieComprobrante sc where sc.estado=1 order by sc.numero")
    List<SerieComprobanteEntity> findAllActivos();
}
