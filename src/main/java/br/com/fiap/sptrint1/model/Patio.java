package br.com.fiap.sptrint1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_patio")
public class Patio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "patio")
    private List<Moto> motos;

    @ManyToMany
    @JoinTable(
            name = "funcionario_patio", // se no Oracle estiver como tb_funcionario_patio, ajuste aqui
            joinColumns = @JoinColumn(name = "patio_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private List<Funcionario> funcionarios;

    @OneToOne(mappedBy = "patio", cascade = CascadeType.ALL)
    private Localizacao localizacao;

    public Patio(Long id, List<Moto> motos, List<Funcionario> funcionarios, Localizacao localizacao) {
        this.id = id;
        this.motos = motos;
        this.funcionarios = funcionarios;
        this.localizacao = localizacao;
    }

    public Patio() {}

    public List<Moto> getMotos() {
        return motos;
    }
    public void setMotos(List<Moto> motos) {
        this.motos = motos;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }
}
