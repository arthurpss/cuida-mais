package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tcc.ceub.cuidamais.entities.Prescricao;

public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {
}