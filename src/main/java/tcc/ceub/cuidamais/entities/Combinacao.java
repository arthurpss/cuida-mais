package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Time;

@Table(name = "combinacao")
@Entity
@Getter
@Setter
@ToString
public class Combinacao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "qtd_dias", nullable = false)
    private Integer qtd_dias;

    @Column(name = "dias", nullable = false)
    private String dias;

    @Column(name = "preco", nullable = false, precision = 8, scale = 2)
    private BigDecimal preco;

    @Column(name = "hora_inicio", nullable = false)
    private Time hora_inicio;

    @Column(name = "hora_fim", nullable = false)
    private Time hora_fim;

    @Column(name = "avaliacao")
    private Integer avaliacao;

    @Column(name = "comentario")
    private String comentario;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "cuidador_cpf", nullable = false)
    private Cuidador cuidador;

    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "paciente_cpf", nullable = false)
    private Paciente paciente;

}