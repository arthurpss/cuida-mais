package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "prontuario")
@Entity
@Getter
@Setter
@ToString
public class Prontuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "resumo", nullable = false)
    private String resumo;

    @Column(name = "observacoes")
    private String observacoes;

    @OneToOne(cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    @JoinColumn(name = "paciente_cpf", nullable = false)
    private Paciente paciente;

}