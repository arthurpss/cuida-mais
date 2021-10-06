package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tcc.ceub.cuidamais.entities.Formacao;

import java.util.List;

public interface FormacaoRepository extends JpaRepository<Formacao, Long> {
    @Query("select f from Formacao f where f.cuidador.cpf = ?1")
    List<Formacao> findByCuidadorCpf(String cpf);
}