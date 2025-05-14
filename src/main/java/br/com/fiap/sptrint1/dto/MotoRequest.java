package br.com.fiap.sptrint1.dto;

import java.time.LocalDate;

public record MotoRequest(String modelo, String cor, String placa, LocalDate dataFabricacao, Long patioId, Long chaveiroId) {
}
