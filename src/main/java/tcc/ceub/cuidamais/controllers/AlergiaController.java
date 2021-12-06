package tcc.ceub.cuidamais.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.ceub.cuidamais.dto.AlergiaDTO;
import tcc.ceub.cuidamais.entities.Alergia;
import tcc.ceub.cuidamais.repositories.AlergiaRepository;
import tcc.ceub.cuidamais.repositories.PacienteRepository;

import java.util.Optional;

@RestController
@RequestMapping("/alergia")
public class AlergiaController {
    final
    AlergiaRepository alergiaRepository;
    final
    PacienteRepository pacienteRepository;

    public AlergiaController(AlergiaRepository alergiaRepository, PacienteRepository pacienteRepository) {
        this.alergiaRepository = alergiaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping("/{cpf}")
    public Optional<AlergiaDTO> getAlergiaByCpf(@PathVariable String cpf) {
        return alergiaRepository.findByPacienteCpf(cpf)
                .map(alergia -> new AlergiaDTO(alergia.getId(), alergia.getAlergia(),
                        alergia.getGrau(), alergia.getPaciente().getCpf()));
    }

    // @GetMapping("/{id}")
    // public Optional<Alergia> getAlergiaById(@PathVariable Long id) {
    //     return alergiaRepository.findById(id);
    // }

    @PostMapping("/{cpf}")
    public Optional<Alergia> create(@PathVariable String cpf, @RequestBody AlergiaDTO alergiaDTO) {
        return pacienteRepository.findByCpf(cpf).map(paciente -> {
            Alergia alergia = new Alergia(alergiaDTO.getAlergia(), alergiaDTO.getGrau(), paciente);
            return alergiaRepository.save(alergia);
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Alergia novaAlergia) {
        return alergiaRepository.findById(id)
                .map(alergia -> {
                    alergia.setAlergia(novaAlergia.getAlergia());
                    alergia.setGrau(novaAlergia.getGrau());
                    Alergia alergiaAtualizada = alergiaRepository.save(alergia);
                    return ResponseEntity.ok().body(alergiaAtualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return alergiaRepository.findById(id)
                .map(alergia -> {
                    alergiaRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}