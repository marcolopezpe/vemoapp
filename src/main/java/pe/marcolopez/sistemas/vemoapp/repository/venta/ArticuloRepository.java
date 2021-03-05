package pe.marcolopez.sistemas.vemoapp.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.marcolopez.sistemas.vemoapp.entity.venta.ArticuloEntity;

import java.util.List;

@Repository
public interface ArticuloRepository extends JpaRepository<ArticuloEntity, Long> {

    @Query("select a from Articulo a where a.estado=1")
    List<ArticuloEntity> findAllActivos();

    @Query(" select m.articulo.id, " +
            "       coalesce(sum(case when m.tipo='E' then m.cantidad else -m.cantidad end), 0) as cantidad, " +
            "       coalesce(sum(case when m.tipo='E' then m.kilos else -m.kilos end), 0) as kilos " +
            "from Movimiento m " +
            "where m.estado=1 " +
            "group by m.articulo.id ")
    List<Object[]> findByFinalStocks();
}
