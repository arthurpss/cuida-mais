package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Table(name = "dispositivo")
@Entity
@Getter
@Setter
@ToString
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "dispositivo", nullable = false)
    private String dispositivo;

    @Column(name = "cuidados")
    private String cuidados;

    @ManyToOne
    @JoinColumn(name = "paciente_cpf")
    private Paciente paciente;
}