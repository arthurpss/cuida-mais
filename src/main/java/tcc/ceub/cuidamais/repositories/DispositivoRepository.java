package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tcc.ceub.cuidamais.entities.Dispositivo;

import java.util.List;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    @Query("select d from Dispositivo d where d.paciente.cpf = ?1")
    List<Dispositivo> findByPacienteCpf(String cpf);
}