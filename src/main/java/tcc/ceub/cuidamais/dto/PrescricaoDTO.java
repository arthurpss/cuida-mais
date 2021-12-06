package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class PrescricaoDTO {
    private Long id;
    private String remedio;
    private String horarios;
    private String via;
    private String paciente_cpf;

    public PrescricaoDTO(Long id, String remedio, String horarios, String via, String paciente_cpf) {
        this.id = id;
        this.remedio = remedio;
        this.horarios = horarios;
        this.via = via;
        this.paciente_cpf = paciente_cpf;
    }
}