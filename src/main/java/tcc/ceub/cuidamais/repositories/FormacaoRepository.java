package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tcc.ceub.cuidamais.entities.Formacao;

public interface FormacaoRepository extends JpaRepository<Formacao, Long> {
}