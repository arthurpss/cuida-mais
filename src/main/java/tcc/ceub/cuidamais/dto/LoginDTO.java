package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String cpf;
    private String senha;
    private boolean ativo;

    public LoginDTO(String cpf, String senha, boolean ativo) {
        this.cpf = cpf;
        this.senha = senha;
        this.ativo = ativo;
    }
}