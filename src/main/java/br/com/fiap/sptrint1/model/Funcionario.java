package br.com.fiap.sptrint1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String rg;
    private String telefone;
    @ManyToMany(mappedBy = "funcionarios")
    private List<Patio> patios;


    public Funcionario(Long id, String nome, String cpf, String email, String rg, String telefone, List<Patio> patios) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.rg = rg;
        this.telefone = telefone;
        this.patios = patios;
    }
    public Funcionario() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Patio> getPatios() {
        return patios;
    }

    public void setPatios(List<Patio> patios) {
        this.patios = patios;
    }
}
