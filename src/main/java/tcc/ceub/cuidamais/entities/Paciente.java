package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Table(name = "paciente")
@Entity
@Getter
@Setter
@ToString
public class Paciente {
    @NotNull(message = "CPF não pode ser nulo")
    @Id
    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @NotNull(message = "Data de cadastro não pode ser nula")
    @Column(name = "data_cadastro", nullable = false)
    private Date data_cadastro;

    @NotNull(message = "Nome não pode ser nulo")
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull(message = "Data de nascimento não pode ser nula")
    @Column(name = "data_nascimento", nullable = false)
    private Date data_nascimento;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = false;

    @NotNull(message = "Senha não pode ser vazia")
    @Column(name = "senha", nullable = false)
    private String senha;

    @NotNull(message = "O campo sexo não pode ser nulo")
    @Column(name = "sexo", nullable = false, length = 1)
    private String sexo;

    @NotNull(message = "O email não pode ser vazio")
    @Email(message = "O campo deve ter o formato de email")
    @Column(name = "email", unique = true, length = 150)
    private String email;

    @NotNull(message = "O número de celular não pode ser nulo")
    @Column(name = "celular", unique = true, length = 12)
    private String celular;

    @Column(name = "telefone", unique = true, length = 12)
    private String telefone;

    @Column(name = "resumo", nullable = false)
    private String resumo;

    @Column(name = "observacoes")
    private String observacoes;

    @NotNull(message = "CEP não pode ser nulo")
    @Column(name = "cep", nullable = false, unique = true, length = 8)
    private String cep;

    @NotNull(message = "UF não pode ser nulo")
    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @NotNull(message = "Cidade não pode ser nula")
    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @NotNull(message = "Logradouro não pode ser nulo")
    @Column(name = "logradouro", nullable = false)
    private String logradouro;
}