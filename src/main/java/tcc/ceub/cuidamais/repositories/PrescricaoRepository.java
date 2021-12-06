package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tcc.ceub.cuidamais.entities.Prescricao;

import java.util.Optional;

public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {
    @Query("select p from Prescricao p where p.paciente.cpf = ?1")
    Optional<Prescricao> findByPacienteCpf(String cpf);
}