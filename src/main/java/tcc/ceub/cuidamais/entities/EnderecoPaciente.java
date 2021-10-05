package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "endereco_paciente")
@Entity
@Getter
@Setter
@ToString
public class EnderecoPaciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = false;

    @Column(name = "cep", nullable = false, unique = true, length = 8)
    private String cep;

    @Column(name = "uf", nullable = false, length = 2)
    private String uf;

    @Column(name = "cidade", nullable = false, length = 100)
    private String cidade;

    @Column(name = "logradouro", nullable = false)
    private String logradouro;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "paciente_cpf", nullable = false)
    private Paciente paciente;

}