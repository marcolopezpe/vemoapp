package pe.marcolopez.sistemas.vemoapp.repository.venta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.marcolopez.sistemas.vemoapp.entity.venta.ClienteEntity;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {

    @Query("select c from Cliente c where c.estado=1")
    List<ClienteEntity> findAllActivos();
}
