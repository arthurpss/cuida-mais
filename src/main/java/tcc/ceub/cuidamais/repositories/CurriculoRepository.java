package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tcc.ceub.cuidamais.entities.Curriculo;

public interface CurriculoRepository extends JpaRepository<Curriculo, Long> {
}