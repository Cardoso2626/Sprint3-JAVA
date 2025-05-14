package br.com.fiap.sptrint1.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class MotoRequestDTO {
    
    @NotBlank(message = "O modelo da moto é obrigatório!")
    @Size(max = 100, message = "O modelo deve ter no máximo 100 caracteres!")
    private String modelo;
    @NotBlank(message = "A cor da moto é obrigatória!")
    @Size(max = 50, message = "A cor da moto deve ter no máximo 20 caracteres!")
    private String cor;
    @NotBlank(message = "A placa da moto é obrigatória!")
    @Pattern(
            regexp = "^[A-Z]{3}[0-9][A-Z0-9][0-9]{2}$",
            message = "A placa deve estar no formato ABC1D23!"
    )
    private String placa;
    @NotNull(message = "A data de fabricação é obrigatória!")
    @PastOrPresent(message = "A data de fabricação não pode ser no futuro!")
    private LocalDate dataFabricacao;

    // Getters e setters

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

}
