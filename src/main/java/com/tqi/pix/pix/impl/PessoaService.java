package com.tqi.pix.pix.impl;

import com.tqi.pix.pix.mapper.PessoaMapper;
import com.tqi.pix.pix.model.Pessoa;
import com.tqi.pix.pix.model.dto.PessoaDTO;
import com.tqi.pix.pix.model.form.PessoaForm;
import com.tqi.pix.pix.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tqi.pix.pix.mapper.PessoaMapper.formParaEntidade;
import static com.tqi.pix.pix.mapper.PessoaMapper.listDeEntidadeParaDto;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public Pessoa salvar(PessoaForm pessoaForm) {
        return pessoaRepository.save(formParaEntidade(pessoaForm));
    }

    public void deletar(Long id){
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        if (optional.isPresent()) {
            pessoaRepository.deleteById(id);
        }
    }

    public Pessoa atualizar (Long id, PessoaForm pessoaForm) {

        Pessoa pessoa = formParaEntidade(pessoaForm);
        Optional<Pessoa> optional = pessoaRepository.findById(id);
        if (optional.isEmpty()) {
            throw new EntityNotFoundException();
        }
        Pessoa pessoaAtualizada = optional.get();
        pessoaAtualizada.setNome(pessoa.getNome());
        pessoaAtualizada.setCpf(pessoa.getCpf());
        pessoaAtualizada.setEmail(pessoa.getEmail());
        return pessoaRepository.save(pessoaAtualizada);

    }

    public List<PessoaDTO> listaDePessoas () {
        var pessoas = pessoaRepository.findAll();
        return listDeEntidadeParaDto(pessoas);
    }

    public PessoaDTO detalharPessoa(Long id) {
        return pessoaRepository.findById(id).map(PessoaMapper::entidadeParaDto).orElseThrow(EntityNotFoundException::new);
    }
}



