package tcc.ceub.cuidamais.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    PasswordEncoder passwordEncoder;

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
        paciente.setSenha(passwordEncoder.encode(paciente.getSenha()));
        return pacienteRepository.save(paciente);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Paciente> update(@PathVariable String cpf, @RequestBody Paciente novoPaciente) {
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
                    paciente.setResumo(novoPaciente.getResumo());
                    paciente.setObservacoes(novoPaciente.getObservacoes());
                    paciente.setCep(novoPaciente.getCep());
                    paciente.setUf(novoPaciente.getUf());
                    paciente.setCidade(novoPaciente.getCidade());
                    paciente.setLogradouro(novoPaciente.getLogradouro());
                    Paciente pacienteAtualizado = pacienteRepository.save(paciente);
                    return ResponseEntity.ok().body(pacienteAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{cpf}/ativo")
    public ResponseEntity<Paciente> updateAtivo(@PathVariable String cpf, @RequestBody Boolean ativo) {
        return pacienteRepository.findByCpf(cpf)
                .map(paciente -> {
                    paciente.setAtivo(ativo);
                    Paciente pacienteAtualizado = pacienteRepository.save(paciente);
                    return ResponseEntity.ok().body(pacienteAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity delete(@PathVariable("cpf") String cpf) {
        return pacienteRepository.findByCpf(cpf)
                .map(paciente -> {
                    pacienteRepository.deleteById(cpf);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}