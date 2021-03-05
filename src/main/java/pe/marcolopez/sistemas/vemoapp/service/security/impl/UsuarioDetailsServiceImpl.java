package pe.marcolopez.sistemas.vemoapp.service.security.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.marcolopez.sistemas.vemoapp.entity.security.UsuarioEntity;
import pe.marcolopez.sistemas.vemoapp.repository.security.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsuarioEntity usuarioEntity = usuarioRepository.loadUsuarioEntityByUsuario(username);

        if (usuarioEntity == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        usuarioEntity.getRoles().forEach(rol -> authorities.add(new SimpleGrantedAuthority(rol.getNombre())));

        return new User(usuarioEntity.getUsuario(), usuarioEntity.getContrasena(), authorities);
    }
}
