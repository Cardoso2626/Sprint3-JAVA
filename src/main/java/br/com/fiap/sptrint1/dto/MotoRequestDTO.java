package br.com.fiap.sptrint1.dto;

import br.com.fiap.sptrint1.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dataFabricacao;
    private Status status;

    // Getters e setters

    public MotoRequestDTO() {

    }
    public MotoRequestDTO(String modelo, String cor, String placa, Status status, LocalDate dataFabricacao) {
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
        this.status = status;
        this.dataFabricacao = dataFabricacao;
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

    public Status getStatus() {
        return status;

    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
