package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tcc.ceub.cuidamais.entities.Comorbidade;

import java.util.List;

public interface ComorbidadeRepository extends JpaRepository<Comorbidade, Long> {
    @Query("select c from Comorbidade c where c.paciente.cpf = ?1")
    List<Comorbidade> findByPacienteCpf(String cpf);
}