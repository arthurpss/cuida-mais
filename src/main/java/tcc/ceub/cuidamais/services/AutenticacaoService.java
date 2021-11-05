package tcc.ceub.cuidamais.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tcc.ceub.cuidamais.entities.Usuario;
import tcc.ceub.cuidamais.repositories.UsuarioRepository;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String cpf_cnpj) throws UsernameNotFoundException {
        Optional<Usuario> optional = repository.findByCpfCnpj(cpf_cnpj);

        if (optional.isPresent()) {
            return optional.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}