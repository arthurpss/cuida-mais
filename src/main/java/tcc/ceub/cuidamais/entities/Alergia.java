package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "alergia")
@Entity
@Getter
@Setter
@ToString
public class Alergia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "alergia", nullable = false)
    private String alergia;

    @Column(name = "grau", nullable = false)
    private Integer grau;

    @ManyToOne
    @JoinColumn(name = "paciente_cpf")
    private Paciente paciente;
}