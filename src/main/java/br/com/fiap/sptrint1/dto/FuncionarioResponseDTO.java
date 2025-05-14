package br.com.fiap.sptrint1.dto;

import br.com.fiap.sptrint1.model.Patio;

import java.util.List;

public record FuncionarioResponseDTO(Long id, String nome, String cpf, String telefone, String email, String rg, List<Long> idPatios) {
}
