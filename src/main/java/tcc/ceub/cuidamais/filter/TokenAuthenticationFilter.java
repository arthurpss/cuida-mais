package tcc.ceub.cuidamais.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import tcc.ceub.cuidamais.entities.Cuidador;
import tcc.ceub.cuidamais.entities.Paciente;
import tcc.ceub.cuidamais.repositories.CuidadorRepository;
import tcc.ceub.cuidamais.repositories.PacienteRepository;
import tcc.ceub.cuidamais.services.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final PacienteRepository pacienteRepository;
    private final CuidadorRepository cuidadorRepository;
    @Autowired
    TokenService tokenService;

    public TokenAuthenticationFilter(TokenService tokenService, PacienteRepository pacienteRepository, CuidadorRepository cuidadorRepository) {
        this.tokenService = tokenService;
        this.pacienteRepository = pacienteRepository;
        this.cuidadorRepository = cuidadorRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String tokenFromHeader = getTokenFromHeader(request);
        boolean tokenValid = tokenService.isTokenValid(tokenFromHeader);
        if (tokenValid) {
            this.authenticate(tokenFromHeader);
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String tokenFromHeader) {
        Integer id = tokenService.getTokenId(tokenFromHeader);
        Optional<Cuidador> optionalCuidador = cuidadorRepository.findByCpf(id.toString());
        Optional<Paciente> optionalPaciente = pacienteRepository.findByCpf(id.toString());
        if (optionalCuidador.isPresent()) {
            Cuidador cuidador = optionalCuidador.get();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(cuidador, cuidador.getSenha());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } else if (optionalPaciente.isPresent()) {
            Paciente paciente = optionalPaciente.get();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(paciente, paciente.getSenha());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

}