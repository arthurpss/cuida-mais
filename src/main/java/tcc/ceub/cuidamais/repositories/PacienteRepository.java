package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tcc.ceub.cuidamais.entities.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, String> {
    @Query("select p from Paciente p where p.cpf = ?1")
    Optional<Paciente> findByCpf(String cpf);

    @Query("select p from Paciente p where p.ativo = true")
    List<Paciente> findByAtivoIsTrue();
}