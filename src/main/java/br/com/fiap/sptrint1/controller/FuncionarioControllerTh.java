package br.com.fiap.sptrint1.controller;

import br.com.fiap.sptrint1.dto.FuncionarioRequestDTO;
import br.com.fiap.sptrint1.dto.FuncionarioResponseDTO;
import br.com.fiap.sptrint1.model.Funcionario;
import br.com.fiap.sptrint1.service.FuncionarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pageFuncionario")
public class FuncionarioControllerTh {
    private FuncionarioService funcionarioService;
    public FuncionarioControllerTh(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    @GetMapping("/lista")
    public String listarFuncionarios(Model model){
        List<Funcionario> funcionarios = funcionarioService.listarFuncionarios();
        model.addAttribute("listarFuncionarios", funcionarios);
        return "listaFuncionario";
    }
    @GetMapping("/cadastro")
    public String cadastroFuncionario(Model model){
        model.addAttribute("funcionario", new Funcionario());
        return "funcionarioCadastro";
    }
    @PostMapping("/cadastrar")
    public String cadastrarLivro(FuncionarioRequestDTO funcionarioRequestDTO, Model model){
        FuncionarioResponseDTO funcionario = funcionarioService.save(funcionarioRequestDTO);
        return listarFuncionarios(model);
    }
}
