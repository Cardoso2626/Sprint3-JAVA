package br.com.fiap.sptrint1.service;

import br.com.fiap.sptrint1.dto.LocalizacaoRequestDTO;
import br.com.fiap.sptrint1.dto.LocalizacaoResponse;
import br.com.fiap.sptrint1.model.Localizacao;
import br.com.fiap.sptrint1.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {
    private LocalizacaoRepository localizacaoRepository;
    public LocalizacaoService(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }


    public LocalizacaoResponse criar (LocalizacaoRequestDTO localizaoRequest){
        Localizacao loc = new Localizacao();

        loc.setEstado(localizaoRequest.estado());
        loc.setEstado(localizaoRequest.cidade());
        loc.setEstado(localizaoRequest.rua());
        loc.setEstado(localizaoRequest.estado());

        loc = localizacaoRepository.save(loc);

        return  new LocalizacaoResponse(
                loc.getId(),
                loc.getRua(),
                loc.getNumero(),
                loc.getCidade(),
                loc.getEstado(),
                null
        );

    }

    public void deletar (Long id){
        localizacaoRepository.deleteById(id);
    }

    public LocalizacaoResponse acharPorRua(String rua) {
        Localizacao loc = localizacaoRepository.findByRua(rua).orElseThrow(() -> new RuntimeException("Não foi possível encontrar a rua"));
        return  new LocalizacaoResponse(
                loc.getId(),
                loc.getRua(),
                loc.getNumero(),
                loc.getCidade(),
                loc.getEstado(),
                loc.getPatio() != null ? loc.getPatio().getId() : null
        );
    }

}
