package tcc.ceub.cuidamais.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tcc.ceub.cuidamais.entities.Cuidador;
import tcc.ceub.cuidamais.repositories.CuidadorRepository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@RestController
@RequestMapping("/cuidador")
public class CuidadorController {

    final
    CuidadorRepository cuidadorRepository;
    final
    PasswordEncoder passwordEncoder;

    public CuidadorController(CuidadorRepository cuidadorRepository, PasswordEncoder passwordEncoder) {
        this.cuidadorRepository = cuidadorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/ativos")
    public List<Cuidador> getCuidadoresAtivos() {
        return cuidadorRepository.findByAtivoIsTrue();
    }

    @GetMapping("/{cpf}")
    public Optional<Cuidador> getCuidadorByCpf(@PathVariable String cpf) {
        return cuidadorRepository.findByCpf(cpf);
    }

    @PostMapping
    public Cuidador create(@RequestBody Cuidador cuidador) {
        LocalDateTime ldt = LocalDateTime.now();
        cuidador.setData_cadastro(Date.valueOf(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH).format(ldt)));
        cuidador.setData_nascimento(Date.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(cuidador.getData_nascimento())));
        cuidador.setSenha(passwordEncoder.encode(cuidador.getSenha()));
        return cuidadorRepository.save(cuidador);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity update(@PathVariable String cpf, @RequestBody Cuidador novoCuidador) {
        return cuidadorRepository.findByCpf(cpf)
                .map(cuidador -> {
                    cuidador.setAtivo(novoCuidador.getAtivo());
                    cuidador.setNome(novoCuidador.getNome());
                    cuidador.setData_nascimento(novoCuidador.getData_nascimento());
                    cuidador.setSenha(novoCuidador.getSenha());
                    cuidador.setPontuacao(novoCuidador.getPontuacao());
                    cuidador.setSexo(novoCuidador.getSexo());
                    cuidador.setCelular(novoCuidador.getCelular());
                    cuidador.setEmail(novoCuidador.getEmail());
//                    cuidador.setTelefone(novoCuidador.getTelefone());
//                    cuidador.setResumo(novoCuidador.getResumo());
//                    cuidador.setObjetivo(novoCuidador.getObjetivo());
                    cuidador.setCep(novoCuidador.getCep());
                    cuidador.setUf(novoCuidador.getUf());
                    cuidador.setCidade(novoCuidador.getCidade());
                    cuidador.setLogradouro(novoCuidador.getLogradouro());
                    cuidador.setLongitude(novoCuidador.getLongitude());
                    cuidador.setLatitude(novoCuidador.getLatitude());
                    Cuidador cuidadorAtualizado = cuidadorRepository.save(cuidador);
                    return ResponseEntity.ok().body(cuidadorAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{cpf}/ativo")
    public ResponseEntity updateAtivo(@PathVariable String cpf, @RequestBody Boolean ativo) {
        return cuidadorRepository.findByCpf(cpf)
                .map(cuidador -> {
                    cuidador.setAtivo(ativo);
                    Cuidador cuidadorAtualizado = cuidadorRepository.save(cuidador);
                    return ResponseEntity.ok().body(cuidadorAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{cpf}/pontuacao")
    public ResponseEntity updatePontuacao(@PathVariable String cpf, @RequestBody Integer pontuacao) {
        return cuidadorRepository.findByCpf(cpf)
                .map(cuidador -> {
                    cuidador.setPontuacao(pontuacao);
                    Cuidador cuidadorAtualizado = cuidadorRepository.save(cuidador);
                    return ResponseEntity.ok().body(cuidadorAtualizado);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity delete(@PathVariable String cpf) {
        return cuidadorRepository.findByCpf(cpf)
                .map(cuidador -> {
                    cuidadorRepository.deleteById(cpf);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}