package br.com.fiap.sptrint1.service;

import br.com.fiap.sptrint1.dto.FuncionarioRequest;
import br.com.fiap.sptrint1.dto.FuncionarioRequestDTO;
import br.com.fiap.sptrint1.dto.FuncionarioResponseDTO;
import br.com.fiap.sptrint1.model.Funcionario;
import br.com.fiap.sptrint1.model.Patio;
import br.com.fiap.sptrint1.repository.FuncionarioRepository;
import br.com.fiap.sptrint1.repository.PatioRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {
    public final FuncionarioRepository funcionarioRepository;
    public final PatioRepository patioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository, PatioRepository patioRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.patioRepository = patioRepository;
    }

    // Criar
    @CachePut(value = "funcionario", key = "#result.id")
    public FuncionarioResponseDTO save(FuncionarioRequestDTO funcionarioDTO) {
        Funcionario funcionario = new Funcionario();

        funcionario.setCpf(funcionarioDTO.getCpf());
        funcionario.setNome(funcionarioDTO.getNome());
        funcionario.setEmail(funcionarioDTO.getEmail());
        funcionario.setRg(funcionarioDTO.getRg());
        funcionario.setTelefone(funcionarioDTO.getTelefone());
        funcionario.setPatios(null);

        funcionario = funcionarioRepository.save(funcionario);

        return new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCpf(),
                funcionario.getTelefone(),
                funcionario.getRg(),
                funcionario.getEmail(),
                null
        );

    }

    // Deletar
    @CacheEvict(value = "funcionario", key = "#id")
    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }

    //Pega funcionário por id
    @Cacheable(value = "funcionario", key = "#id")
    public FuncionarioResponseDTO pegarPorId(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("funcionario não encontrado)"));

        List<Long> ids = funcionario.getPatios().stream().map(Patio::getId).collect(Collectors.toList());
        return  new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getTelefone(),
                funcionario.getRg(),
                funcionario.getCpf(),
                ids
        );

    }

    //Atualizar
    @CacheEvict(value = "funcionario", key = "#id")
    public FuncionarioResponseDTO atualiza(Long id, FuncionarioRequest funcionarioDTO) {
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Funcionario não encontrado"));
        funcionario.setNome(funcionarioDTO.nome());
        funcionario.setEmail(funcionarioDTO.email());
        funcionario.setTelefone(funcionarioDTO.telefone());
        funcionario.setRg(funcionarioDTO.rg());
        funcionario.setCpf(funcionarioDTO.cpf());
        Patio patio = new Patio();

        if(funcionarioDTO.idPatios() != null) {
            List<Patio> patioIds = patioRepository.findAllById(funcionarioDTO.idPatios());
            funcionario.setPatios(patioIds);
        }

        funcionario = funcionarioRepository.save(funcionario);

        List<Long> ids = funcionario.getPatios().stream().map(Patio::getId).collect(Collectors.toList());
        return  new FuncionarioResponseDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getRg(),
                funcionario.getCpf(),
                funcionario.getTelefone(),
                funcionario.getEmail(),
                ids
        );

    }

    public List<Funcionario> listarFuncionarios(){
        return funcionarioRepository.findAll();
    }


    //para th
    public Funcionario update (Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    //para th
    public Funcionario criar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);

    }

    public Funcionario readFuncionario(Long id) {
        return funcionarioRepository.findById(id).orElse(null);
    }


}
