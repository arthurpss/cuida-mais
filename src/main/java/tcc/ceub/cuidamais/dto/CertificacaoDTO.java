package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class CertificacaoDTO {
    private Long id;
    private String instituicao;
    private String titulo;
    private Integer carga_horaria;
    private String cuidador_cpf;

    public CertificacaoDTO(Long id, String instituicao, String titulo, Integer carga_horaria, String cuidador_cpf) {
        this.id = id;
        this.instituicao = instituicao;
        this.titulo = titulo;
        this.carga_horaria = carga_horaria;
        this.cuidador_cpf = cuidador_cpf;
    }
}