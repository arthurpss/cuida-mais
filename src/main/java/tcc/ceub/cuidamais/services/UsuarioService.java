package tcc.ceub.cuidamais.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tcc.ceub.cuidamais.entities.Cuidador;
import tcc.ceub.cuidamais.entities.Paciente;
import tcc.ceub.cuidamais.repositories.CuidadorRepository;
import tcc.ceub.cuidamais.repositories.PacienteRepository;

import java.util.Optional;

@Service("usuarioService")
public class UsuarioService implements UserDetailsService {

    final
    PacienteRepository pacienteRepository;
    final
    CuidadorRepository cuidadorRepository;

    public UsuarioService(PacienteRepository pacienteRepository, CuidadorRepository cuidadorRepository) {
        this.pacienteRepository = pacienteRepository;
        this.cuidadorRepository = cuidadorRepository;
    }

    /*public LoginDTO getLogin(String cpf_cnpj) throws UsernameNotFoundException {
        Optional<Paciente> pacienteOptional = pacienteRepository.findByCpf(cpf_cnpj);
        Optional<Cuidador> cuidadorOptional = cuidadorRepository.findByCpf(cpf_cnpj);
        if (pacienteOptional.isPresent()) {
            Paciente paciente = pacienteOptional.get();
            return new LoginDTO(paciente.getCpf(), paciente.getSenha(), paciente.getAtivo());
        } else if (cuidadorOptional.isPresent()) {
            Cuidador cuidador = cuidadorOptional.get();
            return new LoginDTO(cuidador.getCpf(), cuidador.getSenha(), cuidador.getAtivo());
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado: " + cpf_cnpj);
        }
    }*/

    @Override
    public UserDetails loadUserByUsername(String cpf_cnpj) throws UsernameNotFoundException {

        Optional<Paciente> pacienteOptional = pacienteRepository.findByCpf(cpf_cnpj);
        Optional<Cuidador> cuidadorOptional = cuidadorRepository.findByCpf(cpf_cnpj);
        if (pacienteOptional.isPresent()) {
            return pacienteOptional.get();
//            return new LoginDTO(paciente.getCpf(), paciente.getSenha(), paciente.getAtivo());
        } else if (cuidadorOptional.isPresent()) {
            return cuidadorOptional.get();
//            return new LoginDTO(cuidador.getCpf(), cuidador.getSenha(), cuidador.getAtivo());
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado: " + cpf_cnpj);
        }

        /*UsuarioService usuarioService = new UsuarioService(pacienteRepository, cuidadorRepository);
        try {
            LoginDTO login = usuarioService.getLogin(cpf_cnpj);
            return new Usuario(login);
        } catch (UsernameNotFoundException e) {
            throw e;
        }*/
    }
}