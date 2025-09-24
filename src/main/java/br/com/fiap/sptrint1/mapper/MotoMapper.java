package br.com.fiap.sptrint1.mapper;

import br.com.fiap.sptrint1.dto.MotoResponseDTO;
import br.com.fiap.sptrint1.model.Moto;

public class MotoMapper {
    public static MotoResponseDTO toResponseDTO(Moto moto) {
        return new MotoResponseDTO(
                moto.getId(),
                moto.getModelo(),
                moto.getCor(),
                moto.getPlaca(),
                moto.getDataFabricacao(),
                moto.getStatus(),
                moto.getPatio() != null ? moto.getPatio().getId() : null,
                moto.getChaveiro() != null ? moto.getChaveiro().getId() : null
        );
    }
}
