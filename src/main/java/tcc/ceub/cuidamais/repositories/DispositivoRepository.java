package tcc.ceub.cuidamais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tcc.ceub.cuidamais.entities.Dispositivo;

public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
}