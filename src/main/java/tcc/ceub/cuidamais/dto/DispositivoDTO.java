package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class DispositivoDTO {
    private String dispositivo;
    private String cuidados;

    public void dispositivoDTO(String dispositivo, String cuidados) {
        this.dispositivo = dispositivo;
        this.cuidados = cuidados;
    }
}