package pe.marcolopez.sistemas.vemoapp.controller.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pe.marcolopez.sistemas.vemoapp.dto.security.UsuarioDTO;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioDTO usuarioDTO = new ObjectMapper().readValue(request.getInputStream(), UsuarioDTO.class);

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    usuarioDTO.getUsuario(),
                    usuarioDTO.getContrasena(),
                    new ArrayList<>()
            );

            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        byte[] signingKey = ConstantsSecurity.SUPER_SECRET_KEY.getBytes(StandardCharsets.UTF_8);

        String token = Jwts.builder()
                .setIssuedAt(new Date())
                .setIssuer(ConstantsSecurity.ISSUER_INFO)
                .setSubject(((User) authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + ConstantsSecurity.TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, signingKey)
                .compact();

        response.addHeader("Access-Control-Expose-Headers", "Authorizatoin");
        response.addHeader(ConstantsSecurity.HEADER_AUTHORIZATION_KEY, ConstantsSecurity.TOKEN_BEARER_PREFIX + " " + token);
    }
}
