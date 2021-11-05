package tcc.ceub.cuidamais.filter;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import tcc.ceub.cuidamais.entities.Cuidador;
import tcc.ceub.cuidamais.entities.Paciente;
import tcc.ceub.cuidamais.entities.Usuario;
import tcc.ceub.cuidamais.repositories.CuidadorRepository;
import tcc.ceub.cuidamais.repositories.PacienteRepository;
import tcc.ceub.cuidamais.repositories.UsuarioRepository;
import tcc.ceub.cuidamais.services.TokenService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UsuarioRepository repository;
    private final PacienteRepository pacienteRepository;
    private final CuidadorRepository cuidadorRepository;

    public TokenAuthenticationFilter(TokenService tokenService, UsuarioRepository repository, PacienteRepository pacienteRepository, CuidadorRepository cuidadorRepository) {
        this.tokenService = tokenService;
        this.repository = repository;
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

        Optional<Usuario> optionalUser = repository.findByCpfCnpj(id);

        if (optionalUser.isPresent()) {
            Usuario usuario = optionalUser.get();
            Optional<Paciente> optionalPaciente = pacienteRepository.findByCpf(usuario.getCpf_cnpj());
            Paciente paciente = optionalPaciente.get();
            Optional<Cuidador> optionalCuidador = cuidadorRepository.findByCpf(usuario.getCpf_cnpj());
            Cuidador cuidador = optionalCuidador.get();

            //Buscar cuidador ou paciente, dependendo de qual perfil o CPF/CNPJ estiver atrelado
            if (optionalPaciente.isPresent()) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(paciente, null);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            if (optionalCuidador.isPresent()) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(cuidador, null);
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }

}