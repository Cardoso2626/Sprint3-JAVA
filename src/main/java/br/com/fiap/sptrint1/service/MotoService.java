package br.com.fiap.sptrint1.service;

import br.com.fiap.sptrint1.dto.MotoRequest;
import br.com.fiap.sptrint1.dto.MotoRequestDTO;
import br.com.fiap.sptrint1.dto.MotoResponseDTO;
import br.com.fiap.sptrint1.model.Chaveiro;
import br.com.fiap.sptrint1.model.Moto;
import br.com.fiap.sptrint1.model.Patio;
import br.com.fiap.sptrint1.repository.ChaveiroRepository;
import br.com.fiap.sptrint1.repository.MotoRepository;
import br.com.fiap.sptrint1.repository.PatioRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MotoService {
    public final MotoRepository motoRepository;
    private final PatioRepository patioRepository;
    private final ChaveiroRepository chaveiroRepository;

    public MotoService(MotoRepository motoRepository, PatioRepository patioRepository, ChaveiroRepository chaveiroRepository) {
        this.motoRepository = motoRepository;
        this.patioRepository = patioRepository;
        this.chaveiroRepository = chaveiroRepository;
    }


    public Page<MotoResponseDTO> listarPorPlacaComPaginacao(String placa, int page, int size, String sortField, String sortOrder) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        PageRequest pageRequest = PageRequest.of(page, size, direction, sortField);

        Page<Moto> motosPage = motoRepository.findByPlaca(placa, pageRequest);

        // Convertendo o resultado em MotoResponseDTO
        return motosPage.map(moto -> new MotoResponseDTO(
                moto.getId(),
                moto.getModelo(),
                moto.getCor(),
                moto.getPlaca(),
                moto.getDataFabricacao(),
                moto.getPatio() != null ? moto.getPatio().getId() : null,
                moto.getChaveiro() != null ? moto.getChaveiro().getId() : null
        ));
    }
    //Listando as motos
    @Cacheable(value = "motos")
    public List<Moto> listar(){
        return motoRepository.findAll();
    }

    //Criar
    @CachePut(value = "motos", key = "#result.id")
    public MotoResponseDTO save(MotoRequestDTO motoDTO) {
        Moto moto = new Moto();

        moto.setModelo(motoDTO.getModelo());
        moto.setCor(motoDTO.getCor());
        moto.setPlaca(motoDTO.getPlaca());
        moto.setDataFabricacao(motoDTO.getDataFabricacao());
        moto.setChaveiro(null);
        moto.setPatio(null);

        moto = motoRepository.save(moto);

        return  new MotoResponseDTO(
            moto.getId(),
            moto.getModelo(),
            moto.getCor(),
            moto.getPlaca(),
            moto.getDataFabricacao(),
            null,
            null
        );

    }

    //Deletar
    @CacheEvict(value = "motos", key = "#id")
    public void delete (Long id){
        motoRepository.deleteById(id);
    }

    //Pega moto por id
    @Cacheable(value = "motos", key = "#id")
    public MotoResponseDTO acharPorId(Long id) {
        Moto moto = motoRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possivel achar o id da moto: " + id));
        return new MotoResponseDTO(
                moto.getId(),
                moto.getModelo(),
                moto.getCor(),
                moto.getPlaca(),
                moto.getDataFabricacao(),
                moto.getPatio() != null ? moto.getPatio().getId() : null,
                moto.getChaveiro() != null ? moto.getChaveiro().getId() : null
        );

    }
    @Cacheable(value = "motosPorPlaca", key = "#placa")
    public MotoResponseDTO acharPorPlaca(String placa) {
        Moto moto = motoRepository.findByPlaca(placa).orElseThrow(() -> new RuntimeException("Não foi possível encontrar a placa"));
        return new MotoResponseDTO(
                moto.getId(),
                moto.getModelo(),
                moto.getCor(),
                moto.getPlaca(),
                moto.getDataFabricacao(),
                moto.getPatio() != null ? moto.getPatio().getId() : null,
                moto.getChaveiro() != null ? moto.getChaveiro().getId() : null
        );
    }

    //Atualiza
    @CacheEvict(value = "motos", key = "#id")
    public MotoResponseDTO atualiza(Long id, MotoRequest motoDto) {
        Moto moto = motoRepository.findById(id).orElseThrow(() -> new RuntimeException("Moto não encontrada"));
        moto.setCor(motoDto.cor());
        moto.setPlaca(motoDto.placa());
        moto.setDataFabricacao(motoDto.dataFabricacao());
        moto.setModelo(motoDto.modelo());

        if(motoDto.patioId() != null){
            Patio patio = patioRepository.findById(motoDto.patioId()).orElseThrow(() -> new RuntimeException("Patio não encontrado"));
            moto.setPatio(patio);

        }else {
            moto.setPatio(null);
        }
        if(motoDto.chaveiroId() != null){
            Chaveiro chaveiro = chaveiroRepository.findById(motoDto.chaveiroId()).orElseThrow(() -> new RuntimeException("Chaveiro não encontrado"));
            moto.setChaveiro(chaveiro);
        }else
            moto.setChaveiro(null);

        moto = motoRepository.save(moto);

        return new MotoResponseDTO(
                moto.getId(),
                moto.getModelo(),
                moto.getCor(),
                moto.getPlaca(),
                moto.getDataFabricacao(),
                moto.getPatio() != null ? moto.getPatio().getId() : null,
                moto.getChaveiro() != null ? moto.getPatio().getId() : null
        );

    }

}

