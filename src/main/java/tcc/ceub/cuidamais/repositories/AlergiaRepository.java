package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tcc.ceub.cuidamais.entities.Alergia;

import java.util.List;

public interface AlergiaRepository extends JpaRepository<Alergia, Long> {
    @Query("select a from Alergia a where a.paciente.cpf = ?1")
    List<Alergia> findByPacienteCpf(String cpf);

}