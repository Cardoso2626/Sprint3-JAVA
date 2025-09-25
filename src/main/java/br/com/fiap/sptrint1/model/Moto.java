package br.com.fiap.sptrint1.model;

import br.com.fiap.sptrint1.enums.Status;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_moto")
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "modelo")
    private String modelo;

    @Column(name = "cor")
    private String cor;

    @Column(name = "placa")
    private String placa;

    @Column(name = "DATAFABRICACAO")
    private LocalDate dataFabricacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;


    @ManyToOne
    @JoinColumn(name = "patio_id") // FK para tb_patio.id
    private Patio patio;

    @OneToOne
    @JoinColumn(name = "chaveiro_id") // FK para tb_chaveiro.id
    private Chaveiro chaveiro;



    public Moto() {}

    public Moto(Long id, String modelo, String cor, String placa, LocalDate dataFabricacao, Status status, Chaveiro chaveiro, Patio patio) {
        this.id = id;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.dataFabricacao = dataFabricacao;
        this.status = status;
        this.chaveiro = chaveiro;
        this.patio = patio;
    }

    public Patio getPatio() {
        return patio;
    }
    public void setPatio(Patio patio) {
        this.patio = patio;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }
    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public LocalDate getDataFabricacao() {
        return dataFabricacao;
    }
    public void setDataFabricacao(LocalDate dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Chaveiro getChaveiro() {
        return chaveiro;
    }
    public void setChaveiro(Chaveiro chaveiro) {
        this.chaveiro = chaveiro;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
