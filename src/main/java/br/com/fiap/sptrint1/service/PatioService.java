package br.com.fiap.sptrint1.service;

import br.com.fiap.sptrint1.dto.PatioRequest;
import br.com.fiap.sptrint1.dto.PatioResponseDTO;
import br.com.fiap.sptrint1.model.Funcionario;
import br.com.fiap.sptrint1.model.Moto;
import br.com.fiap.sptrint1.model.Patio;
import br.com.fiap.sptrint1.repository.ChaveiroRepository;
import br.com.fiap.sptrint1.repository.FuncionarioRepository;
import br.com.fiap.sptrint1.repository.MotoRepository;
import br.com.fiap.sptrint1.repository.PatioRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatioService {
    public final PatioRepository patioRepository;
    public final MotoRepository motoRepository;
    public final FuncionarioRepository funcionarioRepository;

    public PatioService(PatioRepository patioRepository, MotoRepository motoRepository, FuncionarioRepository funcionarioRepository) {
        this.patioRepository = patioRepository;
        this.motoRepository = motoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }

    // Listando os patios
    @Cacheable(value = "patios")
    public List<Patio> getAll() {
        return patioRepository.findAll();
    }

    // Criar
    @CachePut(value = "patio", key = "#result.id")
    public PatioResponseDTO salvar(PatioRequest request) {
        List<Moto> motos = motoRepository.findAllById(request.idMotos());
        List<Funcionario> funcionarios = funcionarioRepository.findAllById(request.idFuncionarios());

        Patio patio = new Patio();
        patio.setMotos(motos);
        patio.setFuncionarios(funcionarios);

        patio = patioRepository.save(patio);

        return new PatioResponseDTO(
                patio.getId(),
                patio.getMotos().stream().map(Moto::getId).toList(),
                patio.getFuncionarios().stream().map(Funcionario::getId).toList()
        );
    }

    // Deletar
    @CacheEvict(value = "patio", key = "#id")
    public void delete(Long id) {
        patioRepository.deleteById(id);
    }

    //Pega por id
    @Cacheable(value = "patio", key = "#id")
    public PatioResponseDTO buscarPorId(Long id) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));

        return new PatioResponseDTO(
                patio.getId(),
                patio.getMotos().stream().map(Moto::getId).collect(Collectors.toList()),
                patio.getFuncionarios().stream().map(Funcionario::getId).toList()
        );
    }

    //Atualiza
    @CacheEvict(value = "patio", key = "#id")
    public PatioResponseDTO atualiza(Long id, PatioRequest request) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));

        List<Moto> motos = motoRepository.findAllById(request.idMotos());
        List<Funcionario> funcionarios = funcionarioRepository.findAllById(request.idFuncionarios());

        patio.setMotos(motos);
        patio.setFuncionarios(funcionarios);

        patio = patioRepository.save(patio);

        return new PatioResponseDTO(
                patio.getId(),
                patio.getMotos().stream().map(Moto::getId).toList(),
                patio.getFuncionarios().stream().map(Funcionario::getId).toList()
        );
    }


}
