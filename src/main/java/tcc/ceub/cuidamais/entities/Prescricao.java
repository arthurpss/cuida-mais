package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "prescricao")
@Entity
@Getter
@Setter
@ToString
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "remedio", nullable = false, length = 200)
    private String remedio;

    @Column(name = "horarios", nullable = false)
    private String horarios;

    @Column(name = "via", nullable = false, length = 1)
    private String via;

    @ManyToOne
    @JoinColumn(name = "paciente_cpf")
    private Paciente paciente;
}