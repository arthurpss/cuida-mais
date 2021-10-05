package tcc.ceub.cuidamais.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.ceub.cuidamais.entities.Paciente;
import tcc.ceub.cuidamais.repositories.PacienteRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    final
    PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @GetMapping("/ativos")
    public List<Paciente> getPacientesAtivos() {
        return pacienteRepository.findByAtivoIsTrue();
    }

    @GetMapping("/{cpf}")
    public Optional<Paciente> getPacienteByCpf(@PathVariable String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    @PostMapping
    public Paciente create(@RequestBody Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity update(@PathVariable("cpf") String cpf, @RequestBody Paciente novoPaciente) {
        return pacienteRepository.findByCpf(cpf)
                .map(paciente -> {
                    paciente.setAtivo(novoPaciente.getAtivo());
                    paciente.setNome(novoPaciente.getNome());
                    paciente.setData_nascimento(novoPaciente.getData_nascimento());
                    paciente.setSenha(novoPaciente.getSenha());
                    paciente.setSexo(novoPaciente.getSexo());
                    paciente.setCelular(novoPaciente.getCelular());
                    paciente.setEmail(novoPaciente.getEmail());
                    paciente.setTelefone(novoPaciente.getTelefone());
                    Paciente pacienteAtualizado = pacienteRepository.save(paciente);
                    return ResponseEntity.ok().body(pacienteAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{cpf}/ativo")
    public ResponseEntity updateAtivo(@PathVariable("cpf") String cpf, @RequestBody Boolean ativo) {
        return pacienteRepository.findByCpf(cpf)
                .map(paciente -> {
                    paciente.setAtivo(ativo);
                    Paciente pacienteAtualizado = pacienteRepository.save(paciente);
                    return ResponseEntity.ok().body(pacienteAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("{cpf}")
    public ResponseEntity delete(@PathVariable String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .map(paciente -> {
                    pacienteRepository.deleteById(cpf);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}