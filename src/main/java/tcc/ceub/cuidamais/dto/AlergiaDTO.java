package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class AlergiaDTO {
    private String alergia;
    private Integer grau;
    private String paciente;

    public void alergiaDto(String alergia, Integer grau, String paciente) {
        this.alergia = alergia;
        this.grau = grau;
        this.paciente = paciente;
    }
}