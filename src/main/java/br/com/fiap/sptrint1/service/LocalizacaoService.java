package br.com.fiap.sptrint1.service;

import br.com.fiap.sptrint1.repository.LocalizacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class LocalizacaoService {
    private LocalizacaoRepository localizacaoRepository;
    public LocalizacaoService(LocalizacaoRepository localizacaoRepository) {
        this.localizacaoRepository = localizacaoRepository;
    }

}
