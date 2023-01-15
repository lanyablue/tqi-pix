package com.tqi.pix.pix.repository;

import com.tqi.pix.pix.model.ChavePix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChavePixRepository extends JpaRepository<ChavePix, String> {

    List<ChavePix> findAllByIdPessoa(Long idPessoa);

    Optional<ChavePix> findByChave(String chave);

    boolean existsByChave(String chave);
}
