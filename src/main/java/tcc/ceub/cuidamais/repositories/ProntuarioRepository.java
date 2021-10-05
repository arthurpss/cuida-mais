package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tcc.ceub.cuidamais.entities.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}