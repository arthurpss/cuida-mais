package tcc.ceub.cuidamais.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.ceub.cuidamais.entities.Certificacao;
import tcc.ceub.cuidamais.repositories.CertificacaoRepository;
import tcc.ceub.cuidamais.repositories.CuidadorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/certificacao")
public class CertificacaoController {

    final
    CertificacaoRepository certificacaoRepository;
    final
    CuidadorRepository cuidadorRepository;

    public CertificacaoController(CertificacaoRepository certificacaoRepository, CuidadorRepository cuidadorRepository) {
        this.certificacaoRepository = certificacaoRepository;
        this.cuidadorRepository = cuidadorRepository;
    }

    @GetMapping("/{id}")
    public Optional<Certificacao> getCertificacaoById(@PathVariable Long id) {
        return certificacaoRepository.findById(id);
    }

    @GetMapping("/{cpf}")
    public List<Certificacao> getCertificacoesByCpf(@PathVariable String cpf) {
        return certificacaoRepository.findByCuidadorCpf(cpf);
    }

    @PostMapping("/{cpf}")
    public Optional<Certificacao> create(@PathVariable String cpf, @RequestBody Certificacao certificacao) {
        return cuidadorRepository.findByCpf(cpf).map(cuidador -> {
            certificacao.setCuidador(cuidador);
            return certificacaoRepository.save(certificacao);
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Certificacao novaCertificacao) {
        return certificacaoRepository.findById(id)
                .map(certificacao -> {
                    certificacao.setCarga_horaria(novaCertificacao.getCarga_horaria());
                    certificacao.setInstituicao(novaCertificacao.getInstituicao());
                    certificacao.setTitulo(novaCertificacao.getTitulo());
                    Certificacao certificacaoAtualizada = certificacaoRepository.save(certificacao);
                    return ResponseEntity.ok().body(certificacaoAtualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return certificacaoRepository.findById(id)
                .map(certificacao -> {
                    certificacaoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}