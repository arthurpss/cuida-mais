package tcc.ceub.cuidamais.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "formacao")
@Entity
@Getter
@Setter
@ToString
public class Formacao {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "instituicao", nullable = false)
    private String instituicao;

    @Column(name = "curso", nullable = false)
    private String curso;

    //    M - Matutino, V - Vepertino, N - Noturno, I - Integral
    @Column(name = "periodo", nullable = false, length = 1)
    private String periodo;

    @Column(name = "data_inicio", nullable = false)
    private Date data_inicio;

    @Column(name = "data_formacao")
    private Date data_formacao;

    @Column(name = "em_andamento", nullable = false)
    private Boolean em_andamento = false;

    @ManyToOne
    @JoinColumn(name = "cuidador_cpf")
    private Cuidador cuidador;

}