package br.com.fiap.sptrint1.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_patio")
public class Patio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "patio")
    private List<Moto> motos;
    @ManyToMany
    @JoinTable(
            name = "funcionario_patio",
            joinColumns = @JoinColumn(name = "patio_id"),
            inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private List<Funcionario> funcionarios;


    public Patio(Long id, List<Moto> motos, List<Funcionario> funcionarios) {
        this.id = id;
        this.motos = motos;
        this.funcionarios = funcionarios;
    }

    public Patio() {

    }


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




}
