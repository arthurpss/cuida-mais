package tcc.ceub.cuidamais.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ExperienciaDTO {
    private Long id;
    private String empresa;
    private String cargo;
    private String descricao;
    private Date data_inicio;
    private Date data_fim;
    private Boolean atual;
    private String cuidador_cpf;


    public ExperienciaDTO(Long id, String empresa, String cargo, String descricao, Date data_inicio, Date data_fim, Boolean atual, String cuidador_cpf) {
        this.id = id;
        this.empresa = empresa;
        this.cargo = cargo;
        this.descricao = descricao;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
        this.atual = atual;
        this.cuidador_cpf = cuidador_cpf;
    }
}