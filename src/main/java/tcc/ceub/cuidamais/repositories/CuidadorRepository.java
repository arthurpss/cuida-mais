package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tcc.ceub.cuidamais.entities.Cuidador;

import java.util.List;
import java.util.Optional;

public interface CuidadorRepository extends JpaRepository<Cuidador, String> {
    @Query("select c from Cuidador c where c.cpf = ?1")
    Optional<Cuidador> findByCpf(String cpf);

    @Query("select c from Cuidador c where c.ativo = true")
    List<Cuidador> findByAtivoIsTrue();
}