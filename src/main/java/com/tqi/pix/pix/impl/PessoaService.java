package com.tqi.pix.pix.impl;

import com.tqi.pix.pix.mapper.PessoaMapper;
import com.tqi.pix.pix.model.Pessoa;
import com.tqi.pix.pix.model.form.PessoaForm;
import com.tqi.pix.pix.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa salvar(PessoaForm pessoaForm) {
        return pessoaRepository.save(PessoaMapper.formParaEntidade(pessoaForm));
    }


}
