package br.com.fiap.sptrint1.dto;

import br.com.fiap.sptrint1.enums.Status;

import java.time.LocalDate;

public record MotoRequest(String modelo, String cor, String placa, LocalDate dataFabricacao, Status status, Long patioId, Long chaveiroId) {
}
