package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tcc.ceub.cuidamais.entities.EnderecoPaciente;

public interface EnderecoPacienteRepository extends JpaRepository<EnderecoPaciente, Long> {
}