package br.com.fiap.sptrint1.service;

import br.com.fiap.sptrint1.dto.MotoRequest;
import br.com.fiap.sptrint1.dto.MotoRequestDTO;
import br.com.fiap.sptrint1.dto.MotoResponseDTO;
import br.com.fiap.sptrint1.enums.Status;
import br.com.fiap.sptrint1.mapper.MotoMapper;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Scanner;

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


    // Listando motos por pageable
    public Page<MotoResponseDTO> buscarPorPlacaComDTO(String placa, Pageable pageable) {
        return motoRepository.findByPlaca(placa, pageable)
                .map(MotoMapper::toResponseDTO);
    }

    //Criar
    @CachePut(value = "motos", key = "#result.id")
    public MotoResponseDTO save(MotoRequestDTO motoDTO) {
        Moto moto = new Moto();

        moto.setModelo(motoDTO.getModelo());
        moto.setCor(motoDTO.getCor());
        moto.setPlaca(motoDTO.getPlaca());
        moto.setDataFabricacao(motoDTO.getDataFabricacao());
        moto.setStatus(Status.DISPONIVEL);
        moto.setChaveiro(null);
        moto.setPatio(null);

        moto = motoRepository.save(moto);

        return  new MotoResponseDTO(
            moto.getId(),
            moto.getModelo(),
            moto.getCor(),
            moto.getPlaca(),
            moto.getDataFabricacao(),
                moto.getStatus(),
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
                moto.getStatus(),
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
                moto.getStatus(),
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
        moto.setStatus(motoDto.status());

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
                moto.getStatus(),
                moto.getPatio() != null ? moto.getPatio().getId() : null,
                moto.getChaveiro() != null ? moto.getPatio().getId() : null
        );

    }

    //FLUXO 1

    public void analisarStatusMoto(MotoRequestDTO moto) {
        Scanner tec = new Scanner(System.in);
        System.out.println("A moto está em boas condições? (S/N) ");
        String resposta = tec.nextLine();
        if(resposta.equals("S")){
            moto.setStatus(Status.DISPONIVEL);
        }else if (resposta.equals("N")){
            moto.setStatus(Status.NAO_DISPONIVEL);
        }
        System.out.println("A moto " + moto.getPlaca() + "não está mais disponível!");

    }

    //FLUXO 2

    public void disponibilizarMoto(MotoRequestDTO moto) {
        Scanner tec = new Scanner(System.in);
        System.out.println("Nos fale o relatório da melhoria da moto, caso não tenham sido feitas melhorias, difite 'S' (SAIR):  ");
        String resposta = tec.nextLine();
        if(resposta.equals("S")){
            System.out.println("Volte quando a moto puder rodar");
        }else{
            moto.setStatus(Status.DISPONIVEL);
        }
        System.out.println("A moto " + moto.getPlaca() + " agora está disponível!");
    }

}

