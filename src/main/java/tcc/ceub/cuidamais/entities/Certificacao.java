package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "certificacao")
@Entity
@Getter
@Setter
@ToString
public class Certificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "instituicao", nullable = false)
    private String instituicao;

    @Column(name = "titulo", nullable = false, length = 100)
    private String titulo;

    @Column(name = "carga_horaria")
    private Integer carga_horaria;

    @ManyToOne(cascade = CascadeType.REMOVE, optional = false)
    @JoinColumn(name = "curriculo_id", nullable = false)
    private Curriculo curriculo;

}