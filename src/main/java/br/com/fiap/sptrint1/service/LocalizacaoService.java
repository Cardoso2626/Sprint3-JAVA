package br.com.fiap.sptrint1.service;

import br.com.fiap.sptrint1.dto.LocalizacaoRequestDTO;
import br.com.fiap.sptrint1.dto.LocalizacaoResponse;
import br.com.fiap.sptrint1.model.Localizacao;
import br.com.fiap.sptrint1.model.Patio;
import br.com.fiap.sptrint1.repository.FuncionarioRepository;
import br.com.fiap.sptrint1.repository.LocalizacaoRepository;
import br.com.fiap.sptrint1.repository.PatioRepository;
import org.springframework.stereotype.Service;



@Service
public class LocalizacaoService {
    private final FuncionarioRepository funcionarioRepository;
    private LocalizacaoRepository localizacaoRepository;
    private PatioRepository patioRepository;
    public LocalizacaoService(LocalizacaoRepository localizacaoRepository, FuncionarioRepository funcionarioRepository) {
        this.localizacaoRepository = localizacaoRepository;
        this.patioRepository  = patioRepository;
        this.funcionarioRepository = funcionarioRepository;
    }


    public LocalizacaoResponse criar (LocalizacaoRequestDTO localizaoRequest){
        Localizacao loc = new Localizacao();

        loc.setEstado(localizaoRequest.estado());
        loc.setCidade(localizaoRequest.cidade());
        loc.setRua(localizaoRequest.rua());
        loc.setNumero(localizaoRequest.numero());

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

    public LocalizacaoResponse atualizar(LocalizacaoRequestDTO localizaoRequest, Long id){
        Localizacao loc = localizacaoRepository.findById(id).orElseThrow(() -> new RuntimeException("Localização não encontrada!"));
        loc.setRua(localizaoRequest.rua());
        loc.setNumero(localizaoRequest.numero());
        loc.setCidade(localizaoRequest.cidade());
        loc.setEstado(localizaoRequest.estado());
        if (localizaoRequest.idPatio() != null){
            Patio patio = patioRepository.findById(localizaoRequest.idPatio()).orElseThrow(() -> new RuntimeException("Patio não encontrado!"));
            loc.setPatio(patio);
        }
        else{
            loc.setPatio(null);
        }
        loc = localizacaoRepository.save(loc);
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
