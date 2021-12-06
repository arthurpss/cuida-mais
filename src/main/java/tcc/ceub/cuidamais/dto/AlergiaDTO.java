package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class AlergiaDTO {
    private Long id;
    private String alergia;
    private Integer grau;
    private String paciente_cpf;

    public AlergiaDTO(Long id, String alergia, Integer grau, String paciente_cpf) {
        this.id = id;
        this.alergia = alergia;
        this.grau = grau;
        this.paciente_cpf = paciente_cpf;
    }
}