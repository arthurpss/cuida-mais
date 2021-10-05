package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "curriculo")
@Entity
@Getter
@Setter
@ToString
public class Curriculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "objetivo", nullable = false)
    private String objetivo;

    @Column(name = "resumo", nullable = false)
    private String resumo;

    @OneToOne(cascade = CascadeType.REMOVE, optional = false, orphanRemoval = true)
    @JoinColumn(name = "cuidador_cpf", nullable = false)
    private Cuidador cuidador;

}