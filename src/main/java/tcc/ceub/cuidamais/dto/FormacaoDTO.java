package tcc.ceub.cuidamais.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class FormacaoDTO {
    private Long id;
    private String instituicao;
    private String curso;
    private String periodo;
    private Date data_inicio;
    private Date data_formacao;
    private Boolean em_andamento;
    private String cuidador_cpf;

    public FormacaoDTO(Long id, String instituicao, String curso, String periodo, Date data_inicio, Date data_formacao, Boolean em_andamento, String cuidador_cpf) {
        this.id = id;
        this.instituicao = instituicao;
        this.curso = curso;
        this.periodo = periodo;
        this.data_inicio = data_inicio;
        this.data_formacao = data_formacao;
        this.em_andamento = em_andamento;
        this.cuidador_cpf = cuidador_cpf;
    }
}