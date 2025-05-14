package br.com.fiap.sptrint1.controller;

import br.com.fiap.sptrint1.dto.ChaveiroRequest;
import br.com.fiap.sptrint1.dto.ChaveiroResponseDTO;
import br.com.fiap.sptrint1.model.Chaveiro;
import br.com.fiap.sptrint1.service.ChaveiroService;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chaveiro")
public class ChaveiroController {

    public final ChaveiroService chaveiroService;

    public ChaveiroController(ChaveiroService chaveiroService) {
        this.chaveiroService = chaveiroService;
    }


    @GetMapping
    public List<Chaveiro> listarTodos() {
        return chaveiroService.pegarTodos();
    }

    @GetMapping("/por-dispositivo")
    public ResponseEntity<Page<ChaveiroResponseDTO>> buscarChaveiros(
            @RequestParam String placa,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortOrder
    ) {
        Pageable pageable = PageRequest.of(page, size, sortOrder.equalsIgnoreCase("ASC") ?
                org.springframework.data.domain.Sort.by(sortBy).ascending() : org.springframework.data.domain.Sort.by(sortBy).descending());

        Page<ChaveiroResponseDTO> chaveiros = chaveiroService.buscarPorPlaca(placa, pageable);

        return ResponseEntity.ok(chaveiros);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ChaveiroResponseDTO> pegarPorId(@PathVariable Long id) {
        ChaveiroResponseDTO chaveiro = chaveiroService.acharPorId(id);
        return ResponseEntity.ok(chaveiro);

    }
    @GetMapping("/dispositivo/{dispositivo}")
    public ResponseEntity<ChaveiroResponseDTO> pegarPorDispositivo(@PathVariable String dispositivo) {
        ChaveiroResponseDTO chaveiro = chaveiroService.acharPorDispositivo(dispositivo);
        return ResponseEntity.ok(chaveiro);
    }


    @PostMapping
    public ResponseEntity<ChaveiroResponseDTO> criar(@RequestBody ChaveiroRequest request) {
        ChaveiroResponseDTO response = chaveiroService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        chaveiroService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChaveiroResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody ChaveiroRequest request
    ) {
        ChaveiroResponseDTO response = chaveiroService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }


}
