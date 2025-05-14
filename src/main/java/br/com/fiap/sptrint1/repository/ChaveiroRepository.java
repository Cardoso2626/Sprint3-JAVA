package br.com.fiap.sptrint1.repository;

import br.com.fiap.sptrint1.model.Chaveiro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChaveiroRepository extends JpaRepository<Chaveiro, Long> {
    Optional<Chaveiro> findByDispositivo (String dispositivo);
    Page<Chaveiro> findByDispositivoContainingIgnoreCase(String dispositivo, Pageable pageable);
}
