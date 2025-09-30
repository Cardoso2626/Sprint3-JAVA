package br.com.fiap.sptrint1.dto;


import java.util.List;

public record PatioResponseDTO(Long id, List<Long> idMotos, List<Long> idFuncionarios, Long idLocalizacao ) {
}
