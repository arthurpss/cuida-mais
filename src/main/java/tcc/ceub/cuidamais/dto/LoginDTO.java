package tcc.ceub.cuidamais.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String cpf_cnpj;
    private String senha;
    private boolean ativo;

    public LoginDTO(String cpf_cnpj, String senha, boolean ativo) {
        this.cpf_cnpj = cpf_cnpj;
        this.senha = senha;
        this.ativo = ativo;
    }
}