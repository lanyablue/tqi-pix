package com.tqi.pix.pix.repository;

import com.tqi.pix.pix.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
