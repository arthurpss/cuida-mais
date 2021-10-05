package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "comorbidade")
@Entity
@Getter
@Setter
@ToString
public class Comorbidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "comorbidade", nullable = false)
    private String comorbidade;

    @Column(name = "cuidados")
    private String cuidados;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;

}