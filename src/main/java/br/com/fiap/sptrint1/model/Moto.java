package br.com.fiap.sptrint1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.websocket.OnError;


import java.time.LocalDate;

@Entity
@Table(name = "tb_moto")
public class Moto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String cor;
    private String placa;
    private LocalDate dataFabricacao;
    @ManyToOne
    @JoinColumn(name = "patio_id")
    private Patio patio;

    @OneToOne
    @JoinColumn(name = "chaveiro_id")
    private Chaveiro chaveiro;


    public Moto(){}

    public Moto(Long id, String modelo, String cor, String placa, LocalDate dataFabricacao, Chaveiro chaveiro, Patio patio) {
        this.id = id;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.dataFabricacao = dataFabricacao;
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
}
