package br.com.fiap.sptrint1.mapper;

import br.com.fiap.sptrint1.dto.ChaveiroResponseDTO;
import br.com.fiap.sptrint1.model.Chaveiro;

public class ChaveiroMapper {

    public static ChaveiroResponseDTO toResponseDTO(Chaveiro chaveiro) {
        return new ChaveiroResponseDTO(
                chaveiro.getId(),
                chaveiro.getDispositivo(),
                chaveiro.getMoto() != null ? chaveiro.getMoto().getId() : null
        );
    }
}
