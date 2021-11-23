package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class ComorbidadeDTO {
    private String comorbidade;
    private String cuidados;

    public void dispositivoDTO(String dispositivo, String cuidados) {
        this.comorbidade = dispositivo;
        this.cuidados = cuidados;
    }
}