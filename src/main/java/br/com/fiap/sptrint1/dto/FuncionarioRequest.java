package br.com.fiap.sptrint1.dto;

import java.util.List;

public record FuncionarioRequest(String nome, String cpf, String telefone, String email, String rg, List<Long> idPatios) {
}
