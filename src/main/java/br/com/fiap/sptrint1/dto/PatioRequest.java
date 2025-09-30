package br.com.fiap.sptrint1.dto;

import java.util.List;

public record PatioRequest(List<Long> idMotos, List<Long> idFuncionarios, Long idLocalizacao) {

}
