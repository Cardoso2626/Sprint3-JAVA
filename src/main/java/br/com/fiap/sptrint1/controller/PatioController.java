package br.com.fiap.sptrint1.controller;

import br.com.fiap.sptrint1.dto.PatioRequest;
import br.com.fiap.sptrint1.dto.PatioResponseDTO;
import br.com.fiap.sptrint1.service.PatioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/patio")
public class PatioController {

    public final PatioService patioService;

    public PatioController(PatioService patioService) {
        this.patioService = patioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatioResponseDTO> buscarPorId(@PathVariable Long id) {
        PatioResponseDTO response = patioService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<PatioResponseDTO> criar(@RequestBody PatioRequest request) {
        PatioResponseDTO response = patioService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        patioService.delete(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PatioResponseDTO> atualizar(@PathVariable Long id, @RequestBody PatioRequest request) {
        PatioResponseDTO response = patioService.atualiza(id, request);
        return ResponseEntity.ok(response);
    }
}
