package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class DispositivoDTO {
    private Long id;
    private String dispositivo;
    private String cuidados;
    private String paciente_cpf;

    public DispositivoDTO(Long id, String dispositivo, String cuidados, String paciente_cpf) {
        this.id = id;
        this.dispositivo = dispositivo;
        this.cuidados = cuidados;
        this.paciente_cpf = paciente_cpf;
    }
}