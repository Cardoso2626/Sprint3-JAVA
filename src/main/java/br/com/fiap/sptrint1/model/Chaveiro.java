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
    @OneToOne(mappedBy = "chaveiro")
    private Moto moto;



    public Chaveiro(Long id, String dispositivo, Moto moto) {
        this.id = id;
        this.dispositivo = dispositivo;
        this.moto = moto;
    }
    public Chaveiro() {}


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
