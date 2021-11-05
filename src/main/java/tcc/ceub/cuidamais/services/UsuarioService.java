package tcc.ceub.cuidamais.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tcc.ceub.cuidamais.repositories.CuidadorRepository;
import tcc.ceub.cuidamais.repositories.PacienteRepository;

@Service("usuarioService")
public class UsuarioService {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private CuidadorRepository cuidadorRepository;


}