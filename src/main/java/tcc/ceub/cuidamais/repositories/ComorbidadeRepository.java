package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tcc.ceub.cuidamais.entities.Comorbidade;

public interface ComorbidadeRepository extends JpaRepository<Comorbidade, Long> {
}