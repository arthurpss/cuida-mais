package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class AlergiaDTO {
    private String alergia;
    private Integer grau;

    public void alergiaDto(String alergia, Integer grau) {
        this.alergia = alergia;
        this.grau = grau;
    }
}