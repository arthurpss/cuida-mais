package tcc.ceub.cuidamais.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tcc.ceub.cuidamais.Usuario;
import tcc.ceub.cuidamais.dto.LoginDTO;
import tcc.ceub.cuidamais.repositories.CuidadorRepository;
import tcc.ceub.cuidamais.repositories.PacienteRepository;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    CuidadorRepository cuidadorRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf_cnpj) throws UsernameNotFoundException {

        UsuarioService usuarioService = new UsuarioService(pacienteRepository, cuidadorRepository);
        try {
            LoginDTO login = usuarioService.getLogin(cpf_cnpj);
            return new Usuario(login);
        } catch (UsernameNotFoundException e) {
            throw e;
        }
    }
}