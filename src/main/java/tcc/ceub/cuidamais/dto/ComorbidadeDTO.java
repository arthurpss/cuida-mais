package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class ComorbidadeDTO {
    private Long id;
    private String comorbidade;
    private String cuidados;
    private String paciente_cpf;

    public ComorbidadeDTO(Long id, String comorbidade, String cuidados, String paciente_cpf) {
        this.id = id;
        this.comorbidade = comorbidade;
        this.cuidados = cuidados;
        this.paciente_cpf = paciente_cpf;
    }
}