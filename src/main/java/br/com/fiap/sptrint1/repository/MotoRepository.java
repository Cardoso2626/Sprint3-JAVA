package br.com.fiap.sptrint1.repository;

import br.com.fiap.sptrint1.model.Moto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MotoRepository extends JpaRepository<Moto, Long> {
    Optional<Moto> findByPlaca(String placa);
    Page<Moto> findByPlaca(String placa, Pageable pageable);
}
