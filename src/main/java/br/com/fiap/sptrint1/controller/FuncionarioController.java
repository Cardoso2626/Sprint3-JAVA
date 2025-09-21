package br.com.fiap.sptrint1.controller;

import br.com.fiap.sptrint1.dto.FuncionarioRequest;
import br.com.fiap.sptrint1.dto.FuncionarioRequestDTO;
import br.com.fiap.sptrint1.dto.FuncionarioResponseDTO;
import br.com.fiap.sptrint1.model.Funcionario;
import br.com.fiap.sptrint1.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

    public final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> pegarPorId(@PathVariable Long id) {
        FuncionarioResponseDTO funcionario = funcionarioService.pegarPorId(id);
        return ResponseEntity.ok(funcionario);

    }


    @PostMapping
    public ResponseEntity<FuncionarioResponseDTO> criar(@RequestBody @Valid FuncionarioRequestDTO funcionarioDTO){
        FuncionarioResponseDTO funcionario = funcionarioService.save(funcionarioDTO);
        return  ResponseEntity.ok(funcionario);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        funcionarioService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioResponseDTO> atualizar(@PathVariable Long id, @RequestBody @Valid FuncionarioRequest funcionarioDTO){
        FuncionarioResponseDTO funcionario = funcionarioService.atualiza(id, funcionarioDTO);
        return ResponseEntity.ok(funcionario);
    }


}
