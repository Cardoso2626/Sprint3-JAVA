package br.com.fiap.sptrint1.controller;

import br.com.fiap.sptrint1.dto.LocalizacaoRequestDTO;
import br.com.fiap.sptrint1.dto.LocalizacaoResponse;
import br.com.fiap.sptrint1.dto.MotoResponseDTO;
import br.com.fiap.sptrint1.service.LocalizacaoService;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocalizaoController {
    private LocalizacaoService localizacaoService;
    public LocalizaoController(LocalizacaoService localizacaoService){
        this.localizacaoService = localizacaoService;
    }

    @PostMapping
    public ResponseEntity<LocalizacaoResponse> criar (@RequestBody LocalizacaoRequestDTO localizacaoRequestDTO){
        LocalizacaoResponse response = localizacaoService.criar(localizacaoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
