package br.com.fiap.sptrint1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "tb_chaveiro")
public class Chaveiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "dispositivo")
    private String dispositivo;
    @Column(name = "pos_x")
    private Double posX;
    @Column(name = "pos_y")
    private Double posY;
    @OneToOne(mappedBy = "chaveiro")
    private Moto moto;



    public Chaveiro(Long id, String dispositivo, Double posX, Double posY,Moto moto) {
        this.id = id;
        this.dispositivo = dispositivo;
        this.posX = posX;
        this.posY = posY;
        this.moto = moto;
    }

    public Chaveiro() {}

    public Double getPosX() {
        return posX;
    }

    public void setPosX(Double posX) {
        this.posX = posX;
    }

    public Double getPosY() {
        return posY;
    }

    public void setPosY(Double posY) {
        this.posY = posY;
    }

    public Moto getMoto() {
        return moto;
    }

    public void setMoto(Moto moto) {
        this.moto = moto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(String dispositivo) {
        this.dispositivo = dispositivo;
    }
}
