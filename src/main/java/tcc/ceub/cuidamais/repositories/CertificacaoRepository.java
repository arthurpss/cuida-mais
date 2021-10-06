package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tcc.ceub.cuidamais.entities.Certificacao;

import java.util.List;

public interface CertificacaoRepository extends JpaRepository<Certificacao, Long> {
    @Query("select c from Certificacao c where c.cuidador.cpf = ?1")
    List<Certificacao> findByCuidadorCpf(String cpf);
}