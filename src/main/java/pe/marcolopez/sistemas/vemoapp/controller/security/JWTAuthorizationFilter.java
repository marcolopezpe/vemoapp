package pe.marcolopez.sistemas.vemoapp.controller.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(ConstantsSecurity.HEADER_AUTHORIZATION_KEY);
        if (header == null || !header.startsWith(ConstantsSecurity.TOKEN_BEARER_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(ConstantsSecurity.HEADER_AUTHORIZATION_KEY);

        if (token != null) {
            byte[] signingKey = ConstantsSecurity.SUPER_SECRET_KEY.getBytes(StandardCharsets.UTF_8);
            String usuario = Jwts.parser()
                    .setSigningKey(signingKey)
                    .parseClaimsJws(token.replace(ConstantsSecurity.TOKEN_BEARER_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (usuario != null) {
                return new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>());
            }

            return null;
        }
        return null;
    }
}
