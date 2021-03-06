package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tcc.ceub.cuidamais.entities.Experiencia;

import java.util.Optional;

public interface ExperienciaRepository extends JpaRepository<Experiencia, Long> {
    @Query("select e from Experiencia e where e.cuidador.cpf = ?1")
    Optional<Experiencia> findByCuidadorCpf(String cpf);
}