package tcc.ceub.cuidamais.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.ceub.cuidamais.entities.Experiencia;
import tcc.ceub.cuidamais.repositories.CuidadorRepository;
import tcc.ceub.cuidamais.repositories.ExperienciaRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/experiencia")
public class ExperienciaController {

    final
    ExperienciaRepository experienciaRepository;
    final
    CuidadorRepository cuidadorRepository;

    public ExperienciaController(ExperienciaRepository experienciaRepository, CuidadorRepository cuidadorRepository) {
        this.experienciaRepository = experienciaRepository;
        this.cuidadorRepository = cuidadorRepository;
    }

    @GetMapping("/{cpf}")
    public List<Experiencia> getExperienciasByCpf(@PathVariable String cpf) {
        return experienciaRepository.findByCuidadorCpf(cpf);
    }

    @GetMapping("/{id}")
    public Optional<Experiencia> getExperienciaById(@PathVariable Long id) {
        return experienciaRepository.findById(id);
    }

    @PostMapping("/{cpf}")
    public Optional<Experiencia> create(@PathVariable String cpf, @RequestBody Experiencia experiencia) {
        return cuidadorRepository.findByCpf(cpf).map(cuidador -> {
            experiencia.setCuidador(cuidador);
            return experienciaRepository.save(experiencia);
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Experiencia novaExperiencia) {
        return experienciaRepository.findById(id)
                .map(experiencia -> {
                    experiencia.setAtual(novaExperiencia.getAtual());
                    experiencia.setCargo(novaExperiencia.getCargo());
                    experiencia.setData_inicio(novaExperiencia.getData_inicio());
                    experiencia.setData_fim(novaExperiencia.getData_fim());
                    experiencia.setDescricao(novaExperiencia.getDescricao());
                    experiencia.setEmpresa(novaExperiencia.getEmpresa());
                    Experiencia experienciaAtualizada = experienciaRepository.save(experiencia);
                    return ResponseEntity.ok().body(experienciaAtualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return experienciaRepository.findById(id)
                .map(experiencia -> {
                    experienciaRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}