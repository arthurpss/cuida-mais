package tcc.ceub.cuidamais.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.ceub.cuidamais.entities.Comorbidade;
import tcc.ceub.cuidamais.repositories.ComorbidadeRepository;
import tcc.ceub.cuidamais.repositories.PacienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comorbidade")
public class ComorbidadeController {
    final
    ComorbidadeRepository comorbidadeRepository;
    final
    PacienteRepository pacienteRepository;

    public ComorbidadeController(ComorbidadeRepository comorbidadeRepository, PacienteRepository pacienteRepository) {
        this.comorbidadeRepository = comorbidadeRepository;
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping("/{cpf}")
    public List<Comorbidade> getComorbidadesByCpf(@PathVariable String cpf) {
        return comorbidadeRepository.findByPacienteCpf(cpf);
    }

    @GetMapping("/{id}")
    public Optional<Comorbidade> getComorbidadeById(@PathVariable Long id) {
        return comorbidadeRepository.findById(id);
    }

    @PostMapping("/{cpf}")
    public Optional<Comorbidade> create(@PathVariable String cpf, @RequestBody Comorbidade comorbidade) {
        return pacienteRepository.findByCpf(cpf).map(paciente -> {
            comorbidade.setPaciente(paciente);
            return comorbidadeRepository.save(comorbidade);
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Comorbidade novaComorbidade) {
        return comorbidadeRepository.findById(id)
                .map(comorbidade -> {
                    comorbidade.setComorbidade(novaComorbidade.getComorbidade());
                    comorbidade.setCuidados(novaComorbidade.getCuidados());
                    Comorbidade comorbidadeAtualizada = comorbidadeRepository.save(comorbidade);
                    return ResponseEntity.ok().body(comorbidadeAtualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return comorbidadeRepository.findById(id)
                .map(comorbidade -> {
                    comorbidadeRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}