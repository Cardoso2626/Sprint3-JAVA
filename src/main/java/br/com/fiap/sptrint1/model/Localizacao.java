package br.com.fiap.sptrint1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_localizacao")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rua")
    private String rua;

    @Column(name = "numero")
    private int numero;

    @Column(name = "cidade")
    private String cidade;

    @Column(name = "estado")
    private String estado;

    @OneToOne
    @JoinColumn(name = "patio_id") // FK em tb_localizacao para tb_patio.id
    private Patio patio;

    public Localizacao() {}

    public Localizacao(Long id, String rua, int numero, String cidade, String estado, Patio patio) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.patio = patio;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }
    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Patio getPatio() {
        return patio;
    }
    public void setPatio(Patio patio) {
        this.patio = patio;
    }
}
