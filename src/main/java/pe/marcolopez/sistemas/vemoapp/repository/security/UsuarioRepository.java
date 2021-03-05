package pe.marcolopez.sistemas.vemoapp.repository.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.marcolopez.sistemas.vemoapp.entity.security.UsuarioEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    @Query("select u from Usuario u where upper(u.usuario)=upper(:usuario) and u.estado=1")
    UsuarioEntity loadUsuarioEntityByUsuario(String usuario);
}
