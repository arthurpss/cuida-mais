package tcc.ceub.cuidamais.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.ceub.cuidamais.dto.DispositivoDTO;
import tcc.ceub.cuidamais.entities.Dispositivo;
import tcc.ceub.cuidamais.repositories.DispositivoRepository;
import tcc.ceub.cuidamais.repositories.PacienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dispositivo")
public class DispositivoController {
    final
    DispositivoRepository dispositivoRepository;
    final
    PacienteRepository pacienteRepository;

    public DispositivoController(DispositivoRepository dispositivoRepository, PacienteRepository pacienteRepository) {
        this.dispositivoRepository = dispositivoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping("/{cpf}")
    public List<Dispositivo> getDispositivosByCpf(@PathVariable String cpf) {
        return dispositivoRepository.findByPacienteCpf(cpf);
    }

    @GetMapping("/{id}")
    public Optional<Dispositivo> getDispositivoById(@PathVariable Long id) {
        return dispositivoRepository.findById(id);
    }

    @PostMapping("/{cpf}")
    public Optional<Dispositivo> create(@PathVariable String cpf, @RequestBody DispositivoDTO dispositivoDTO) {
        return pacienteRepository.findByCpf(cpf).map(paciente -> dispositivoRepository.save(new Dispositivo(dispositivoDTO.getDispositivo(), dispositivoDTO.getCuidados(), paciente)));
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Dispositivo novoDispositivo) {
        return dispositivoRepository.findById(id)
                .map(dispositivo -> {
                    dispositivo.setDispositivo(novoDispositivo.getDispositivo());
                    dispositivo.setCuidados(novoDispositivo.getCuidados());
                    Dispositivo dispositivoAtualizado = dispositivoRepository.save(dispositivo);
                    return ResponseEntity.ok().body(dispositivoAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return dispositivoRepository.findById(id)
                .map(dispositivo -> {
                    dispositivoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}