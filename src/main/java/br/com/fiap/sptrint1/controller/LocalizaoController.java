package br.com.fiap.sptrint1.controller;

import br.com.fiap.sptrint1.dto.LocalizacaoRequestDTO;
import br.com.fiap.sptrint1.dto.LocalizacaoResponse;
import br.com.fiap.sptrint1.service.LocalizacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localizacao")
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

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        localizacaoService.deletar(id);
    }

    @GetMapping("/porRua/{rua}")
    public ResponseEntity<LocalizacaoResponse> buscarPorRua(@PathVariable String rua){
        LocalizacaoResponse response = localizacaoService.acharPorRua(rua);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<LocalizacaoResponse> atualizar (@PathVariable Long id, @RequestBody LocalizacaoRequestDTO localizacaDTO){
        LocalizacaoResponse loc = localizacaoService.atualizar(localizacaDTO, id);
        return ResponseEntity.ok(loc);
    }
}
