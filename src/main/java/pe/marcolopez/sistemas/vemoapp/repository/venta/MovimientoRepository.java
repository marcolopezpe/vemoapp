package pe.marcolopez.sistemas.vemoapp.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.marcolopez.sistemas.vemoapp.entity.venta.MovimientoEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<MovimientoEntity, Long> {

    @Query("select m from Movimiento m where m.estado=1")
    List<MovimientoEntity> findAllActivos();

    @Query("select m " +
            " from Movimiento m " +
            " where m.estado=1 " +
            " and m.fecha >= :desde and m.fecha <= :hasta " +
            " and concat(m.articulo.codigo, m.articulo.descripcion, m.articulo.unidadMedida.nombre) like concat('%', trim(:descripcion), '%') " +
            " and m.tipo like concat('%', trim(:tipo), '%') " +
            " order by m.fecha desc, m.id desc ")
    List<MovimientoEntity> findAllByFiltro(@Param("desde") Date desde,
                                           @Param("hasta") Date hasta,
                                           @Param("descripcion") String descripcion,
                                           @Param("tipo") String tipo);

    @Modifying
    @Query("delete from Movimiento where comprobanteNumero=:comprobanteNumero")
    void deleteAllByComprobanteNumero(@Param("comprobanteNumero") String comprobanteNumero);

    @Modifying
    @Query("update Movimiento set estado=0 where comprobanteNumero=:comprobanteNumero")
    void updateEstadoByComprobanteNumero(@Param("comprobanteNumero") String comprobanteNumero);
}
