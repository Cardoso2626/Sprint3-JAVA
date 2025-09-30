package br.com.fiap.sptrint1.service;

import br.com.fiap.sptrint1.dto.PatioRequest;
import br.com.fiap.sptrint1.dto.PatioResponseDTO;
import br.com.fiap.sptrint1.model.Funcionario;
import br.com.fiap.sptrint1.model.Localizacao;
import br.com.fiap.sptrint1.model.Moto;
import br.com.fiap.sptrint1.model.Patio;
import br.com.fiap.sptrint1.repository.FuncionarioRepository;
import br.com.fiap.sptrint1.repository.LocalizacaoRepository;
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
    public final LocalizacaoRepository localizacaoRepository;

    public PatioService(PatioRepository patioRepository, MotoRepository motoRepository, FuncionarioRepository funcionarioRepository, LocalizacaoRepository localizacaoRepository) {
        this.patioRepository = patioRepository;
        this.motoRepository = motoRepository;
        this.funcionarioRepository = funcionarioRepository;
        this.localizacaoRepository = localizacaoRepository;
    }


    // Criar
    @CachePut(value = "patio", key = "#result.id")
    public PatioResponseDTO salvar(PatioRequest request) {
        List<Moto> motos = motoRepository.findAllById(request.idMotos());
        List<Funcionario> funcionarios = funcionarioRepository.findAllById(request.idFuncionarios());
        Localizacao loc = localizacaoRepository.findById(request.idLocalizacao()).orElseThrow(() -> new RuntimeException("Id não encontrado"));

        Patio patio = new Patio();
        patio.setMotos(motos);
        patio.setFuncionarios(funcionarios);
        patio.setLocalizacao(loc);
        loc.setPatio(patio);

        patio = patioRepository.save(patio);

        return new PatioResponseDTO(
                patio.getId(),
                patio.getMotos().stream().map(Moto::getId).toList(),
                patio.getFuncionarios().stream().map(Funcionario::getId).toList(),
                patio.getLocalizacao() != null ? patio.getLocalizacao().getId() : null
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
                patio.getFuncionarios().stream().map(Funcionario::getId).toList(),
                patio.getLocalizacao() != null ? patio.getLocalizacao().getId() : null

        );
    }

    //Atualiza
    @CacheEvict(value = "patio", key = "#id")
    public PatioResponseDTO atualiza(Long id, PatioRequest request) {
        Patio patio = patioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pátio não encontrado"));

        List<Moto> motos = motoRepository.findAllById(request.idMotos());
        List<Funcionario> funcionarios = funcionarioRepository.findAllById(request.idFuncionarios());
        Localizacao loc = localizacaoRepository.findById(request.idLocalizacao()).orElseThrow(() -> new RuntimeException("Id não encontrado"));


        patio.setMotos(motos);
        patio.setFuncionarios(funcionarios);
        patio.setLocalizacao(loc);

        patio = patioRepository.save(patio);

        return new PatioResponseDTO(
                patio.getId(),
                patio.getMotos().stream().map(Moto::getId).toList(),
                patio.getFuncionarios().stream().map(Funcionario::getId).toList(),
                patio.getLocalizacao() != null ? patio.getLocalizacao().getId() : null
        );
    }


}
