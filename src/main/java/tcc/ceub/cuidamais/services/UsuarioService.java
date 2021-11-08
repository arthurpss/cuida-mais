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

    @Override
    public UserDetails loadUserByUsername(String cpf_cnpj) throws UsernameNotFoundException {

        Optional<Paciente> pacienteOptional = pacienteRepository.findByCpf(cpf_cnpj);
        Optional<Cuidador> cuidadorOptional = cuidadorRepository.findByCpf(cpf_cnpj);
        if (pacienteOptional.isPresent()) {
            return pacienteOptional.get();
        } else if (cuidadorOptional.isPresent()) {
            return cuidadorOptional.get();
        } else {
            throw new UsernameNotFoundException("Usuário não encontrado: " + cpf_cnpj);
        }
    }
}