package br.com.fiap.sptrint1.controller;

import br.com.fiap.sptrint1.dto.MotoRequest;
import br.com.fiap.sptrint1.dto.MotoRequestDTO;
import br.com.fiap.sptrint1.dto.MotoResponseDTO;
import br.com.fiap.sptrint1.service.MotoService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/moto")
public class MotoController {

    //injeção de dependencia
    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoResponseDTO> pegarPorId(@PathVariable Long id) {
        MotoResponseDTO dto = motoService.acharPorId(id);
        return  ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<MotoResponseDTO> criar(@RequestBody @Valid MotoRequestDTO motoRequestDTO) {
        MotoResponseDTO response = motoService.save(motoRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){ motoService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoResponseDTO> atualizarMoto(@PathVariable Long id, @RequestBody @Valid MotoRequest motoDto) {
        MotoResponseDTO moto = motoService.atualiza(id, motoDto);
        return  ResponseEntity.ok(moto);
    }
    @GetMapping("/placa/{placa}")
    public ResponseEntity<MotoResponseDTO> pesquisarPorPlaca(@PathVariable String placa) {
        MotoResponseDTO moto = motoService.acharPorPlaca(placa);
        return ResponseEntity.ok(moto);
    }

    //Pageable
    @GetMapping("/por-placa")
    public ResponseEntity<Page<MotoResponseDTO>> buscarPorPlaca(
            @RequestParam String placa,
            @PageableDefault(size = 10, sort = "modelo") Pageable pageable
    ) {
        Page<MotoResponseDTO> pagina = motoService.buscarPorPlacaComDTO(placa, pageable);
        return ResponseEntity.ok(pagina);
    }
}
