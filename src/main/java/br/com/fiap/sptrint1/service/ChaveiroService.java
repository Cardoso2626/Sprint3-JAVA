package br.com.fiap.sptrint1.service;

import br.com.fiap.sptrint1.dto.ChaveiroRequest;
import br.com.fiap.sptrint1.dto.ChaveiroResponseDTO;
import br.com.fiap.sptrint1.model.Chaveiro;
import br.com.fiap.sptrint1.model.Moto;
import br.com.fiap.sptrint1.repository.ChaveiroRepository;
import br.com.fiap.sptrint1.repository.MotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChaveiroService {
    public final ChaveiroRepository chaveiroRepository;
    public final MotoRepository motoRepository;

    public ChaveiroService(ChaveiroRepository chaveiroRepository, MotoRepository motoRepository) {
        this.motoRepository = motoRepository;
        this.chaveiroRepository = chaveiroRepository;
    }

    // Listando os chaveiros

    public Page<ChaveiroResponseDTO> buscarPorPlaca(String placa, Pageable pageable) {
        Page<Chaveiro> chaveiros = chaveiroRepository.findByMotoPlacaContainingIgnoreCase(placa, pageable);

        return chaveiros.map(chaveiro -> new ChaveiroResponseDTO(
                chaveiro.getId(),
                chaveiro.getDispositivo(),
                chaveiro.getMoto() != null ? chaveiro.getMoto().getId() : null
        ));
    }

    @Cacheable(value = "chaveiros")
    public List<Chaveiro> pegarTodos() {
        return chaveiroRepository.findAll();
    }

    // Criar
    @CachePut(value = "chaveiro", key = "#result.id")
    public ChaveiroResponseDTO cadastrar(ChaveiroRequest request) {
        Chaveiro chaveiro = new Chaveiro();
        chaveiro.setDispositivo(request.dispositivo());

        if (request.motoId() != null) {
            Moto moto = motoRepository.findById(request.motoId())
                    .orElseThrow(() -> new RuntimeException("Moto não encontrada"));
            chaveiro.setMoto(moto);
        }

        chaveiro = chaveiroRepository.save(chaveiro);

        Long motoId = chaveiro.getMoto() != null ? chaveiro.getMoto().getId() : null;

        return new ChaveiroResponseDTO(
                chaveiro.getId(),
                chaveiro.getDispositivo(),
                motoId
        );
    }


    // Deletar
    @CacheEvict(value = "chaveiro", key = "#id")
    public void delete(Long id) {
        chaveiroRepository.deleteById(id);
    }
    //Pega por id
    @Cacheable(value = "chaveiro", key = "#id")
    public ChaveiroResponseDTO acharPorId(Long id){
        Chaveiro chaveiro = chaveiroRepository.findById(id).orElseThrow(() -> new RuntimeException("Chaveiro não encontrado"));
        return new ChaveiroResponseDTO(
                chaveiro.getId(),
                chaveiro.getDispositivo(),
                chaveiro.getMoto() != null  ? chaveiro.getMoto().getId() : null
        );
    }
    @Cacheable(value = "chaveirosPorDispositivo", key = "#dispositivo")
    public ChaveiroResponseDTO acharPorDispositivo(String dispositivo) {
        Chaveiro chaveiro = chaveiroRepository.findByDispositivo(dispositivo).orElseThrow(() -> new RuntimeException("Não foi possivel identificar o dispositivo do chaveiro!"));
        return new ChaveiroResponseDTO(
                chaveiro.getId(),
                chaveiro.getDispositivo(),
                chaveiro.getMoto() != null ? chaveiro.getMoto().getId() : null
        );
    }

    //Atualizar
    @CacheEvict(value = "chaveiro", key = "#id")
    public ChaveiroResponseDTO atualizar(Long id, ChaveiroRequest request) {
        Chaveiro chaveiro = chaveiroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Chaveiro não encontrado"));

        chaveiro.setDispositivo(request.dispositivo());

        if (request.motoId() != null) {
            Moto moto = motoRepository.findById(request.motoId())
                    .orElseThrow(() -> new RuntimeException("Moto não encontrada"));
            chaveiro.setMoto(moto);
        } else {
            chaveiro.setMoto(null); // Se vier null, desvincula a moto
        }

        chaveiro = chaveiroRepository.save(chaveiro);

        Long motoId = chaveiro.getMoto() != null ? chaveiro.getMoto().getId() : null;

        return new ChaveiroResponseDTO(
                chaveiro.getId(),
                chaveiro.getDispositivo(),
                motoId
        );
    }


}
