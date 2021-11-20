package tcc.ceub.cuidamais.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import tcc.ceub.cuidamais.entities.Cuidador;
import tcc.ceub.cuidamais.entities.Paciente;

import java.util.Date;

@Service
public class TokenService {
    @Value("${expiration}")
    private String expiration;

    @Value("${secret}")
    private String secret;

    public String generateToken(Authentication authentication) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + Long.parseLong(expiration));

        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_PACIENTE"))) {
            Paciente paciente = (Paciente) authentication.getPrincipal();
            return Jwts.builder().setIssuer("cuida-mais").setSubject(paciente.getUsername()).setIssuedAt(new Date())
                    .setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret).compact();
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_CUIDADOR"))) {
            Cuidador cuidador = (Cuidador) authentication.getPrincipal();
            return Jwts.builder().setIssuer("cuida-mais").setSubject(cuidador.getUsername()).setIssuedAt(new Date())
                    .setExpiration(exp).signWith(SignatureAlgorithm.HS256, secret).compact();
        } else {
            return "";
        }
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getTokenId(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return String.valueOf(body.getSubject());
    }
}