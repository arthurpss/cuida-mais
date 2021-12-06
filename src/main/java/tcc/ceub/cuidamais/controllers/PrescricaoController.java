package tcc.ceub.cuidamais.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.ceub.cuidamais.dto.PrescricaoDTO;
import tcc.ceub.cuidamais.entities.Prescricao;
import tcc.ceub.cuidamais.repositories.PacienteRepository;
import tcc.ceub.cuidamais.repositories.PrescricaoRepository;

import java.util.Optional;

@RestController
@RequestMapping("/prescricao")
public class PrescricaoController {
    final
    PrescricaoRepository prescricaoRepository;
    final
    PacienteRepository pacienteRepository;

    public PrescricaoController(PrescricaoRepository prescricaoRepository, PacienteRepository pacienteRepository) {
        this.prescricaoRepository = prescricaoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping("/{id}")
    public Optional<Prescricao> getPrescricaoById(@PathVariable Long id) {
        return prescricaoRepository.findById(id);
    }

    @GetMapping("/{cpf}")
    public Optional<PrescricaoDTO> getPrescricoesByCpf(@PathVariable String cpf) {
        return prescricaoRepository.findByPacienteCpf(cpf)
                .map(prescricao -> new PrescricaoDTO(prescricao.getId(), prescricao.getRemedio(),
                        prescricao.getHorarios(), prescricao.getVia(), prescricao.getPaciente().getCpf()));
    }

    @PostMapping("/{cpf}")
    public Optional<Prescricao> create(@PathVariable String cpf, @RequestBody Prescricao prescricao) {
        return pacienteRepository.findByCpf(cpf).map(paciente -> {
            prescricao.setPaciente(paciente);
            return prescricaoRepository.save(prescricao);
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Prescricao novaPrescricao) {
        return prescricaoRepository.findById(id)
                .map(prescricao -> {
                    prescricao.setHorarios(novaPrescricao.getHorarios());
                    prescricao.setRemedio(novaPrescricao.getRemedio());
                    prescricao.setVia(novaPrescricao.getVia());
                    Prescricao prescricaoAtualizada = prescricaoRepository.save(prescricao);
                    return ResponseEntity.ok().body(prescricaoAtualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return prescricaoRepository.findById(id)
                .map(prescricao -> {
                    prescricaoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}