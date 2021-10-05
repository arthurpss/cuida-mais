package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tcc.ceub.cuidamais.entities.Certificacao;

public interface CertificacaoRepository extends JpaRepository<Certificacao, Long> {
}