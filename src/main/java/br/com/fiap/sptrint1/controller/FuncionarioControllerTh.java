package br.com.fiap.sptrint1.controller;

import br.com.fiap.sptrint1.model.Funcionario;
import br.com.fiap.sptrint1.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    public String cadastrarLivro(@Valid Funcionario funcionario, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("funcionario",funcionario);
            return "funcionarioCadastro";
        }
        if (funcionario.getId() == null){
            funcionarioService.criar(funcionario);
        }else{
            funcionarioService.update(funcionario);
        }
        return listarFuncionarios(model);
    }

    @GetMapping("/cadastro/{id}")
    public String cadastroFuncionario(@PathVariable Long id, Model model){
        Funcionario funcionario = funcionarioService.readFuncionario(id);
        if(funcionario == null){
            listarFuncionarios(model);
        }
        model.addAttribute("funcionario", funcionario);
        return "funcionarioCadastro";
    }
    @GetMapping("/deletar/{id}")
    public String deletarFuncionario(@PathVariable Long id, Model model){
        funcionarioService.delete(id);
        return listarFuncionarios(model);
    }
}
