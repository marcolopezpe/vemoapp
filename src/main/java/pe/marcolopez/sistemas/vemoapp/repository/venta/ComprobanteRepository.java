package pe.marcolopez.sistemas.vemoapp.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.marcolopez.sistemas.vemoapp.entity.venta.ComprobanteEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface ComprobanteRepository extends JpaRepository<ComprobanteEntity, Long> {

    @Query("select c from Comprobante c where c.estado=1 order by c.fecha desc, c.serie desc, c.numero desc")
    List<ComprobanteEntity> findAllActivos();

    @Query("  select c " +
            " from Comprobante c " +
            " where c.estado=1 " +
            " and c.fecha >= :desde and c.fecha <= :hasta " +
            " and c.cliente.razonSocial like concat('%', trim(:cliente), '%')" +
            " and concat(c.serie, '-', c.numero) like concat('%', trim(:numero), '%') " +
            " order by c.fecha desc, c.serie desc, c.numero desc ")
    List<ComprobanteEntity> findAllByFiltro(@Param("desde") Date desde,
                                            @Param("hasta") Date hasta,
                                            @Param("numero") String numero,
                                            @Param("cliente") String cliente);
}
