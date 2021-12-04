package tcc.ceub.cuidamais.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tcc.ceub.cuidamais.entities.Formacao;
import tcc.ceub.cuidamais.repositories.CuidadorRepository;
import tcc.ceub.cuidamais.repositories.FormacaoRepository;

import java.sql.Date;
import java.util.Optional;

@RestController
@RequestMapping("/formacao")
public class FormacaoController {

    final
    FormacaoRepository formacaoRepository;
    final
    CuidadorRepository cuidadorRepository;

    public FormacaoController(FormacaoRepository formacaoRepository, CuidadorRepository cuidadorRepository) {
        this.formacaoRepository = formacaoRepository;
        this.cuidadorRepository = cuidadorRepository;
    }

    /*@GetMapping("/{id}")
    public Optional<Formacao> getFormacaoById(@PathVariable Long id) {
        return formacaoRepository.findById(id);
    }*/

    @GetMapping("/{cpf}")
    public Optional<Formacao> getFormacoesByCpf(@PathVariable String cpf) {
        return formacaoRepository.findByCuidadorCpf(cpf);
    }

    @PostMapping("/{cpf}")
    public Optional<Formacao> create(@PathVariable String cpf, @RequestBody Formacao formacao) {
        return cuidadorRepository.findByCpf(cpf).map(cuidador -> {
            formacao.setCuidador(cuidador);
            return formacaoRepository.save(formacao);
        });
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody Formacao novaFormacao) {
        return formacaoRepository.findById(id)
                .map(formacao -> {
                    formacao.setData_formacao(novaFormacao.getData_formacao());
                    formacao.setCurso(novaFormacao.getCurso());
                    formacao.setData_inicio(Date.valueOf(novaFormacao.getData_inicio().toString()));
                    formacao.setData_formacao(Date.valueOf(novaFormacao.getData_formacao().toString()));
                    formacao.setEm_andamento(novaFormacao.getEm_andamento());
                    formacao.setInstituicao(novaFormacao.getInstituicao());
                    formacao.setPeriodo(novaFormacao.getPeriodo());
                    Formacao formacaoAtualizada = formacaoRepository.save(formacao);
                    return ResponseEntity.ok().body(formacaoAtualizada);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        return formacaoRepository.findById(id)
                .map(formacao -> {
                    formacaoRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}