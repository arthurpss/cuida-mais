package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "experiencia")
@Entity
@Getter
@Setter
@ToString
public class Experiencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "empresa", nullable = false)
    private String empresa;

    @Column(name = "cargo", nullable = false)
    private String cargo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private Date data_inicio;

    @Column(name = "data_fim")
    private Date data_fim;

    @Column(name = "atual", nullable = false)
    private Boolean atual = false;

    @ManyToOne
    @JoinColumn(name = "cuidador_cpf")
    private Cuidador cuidador;

}