package com.tqi.pix.pix.impl;

import com.tqi.pix.pix.mapper.PessoaMapper;
import com.tqi.pix.pix.model.Pessoa;
import com.tqi.pix.pix.model.dto.PessoaComChavePixDTO;
import com.tqi.pix.pix.model.form.PessoaForm;
import com.tqi.pix.pix.repository.ChavePixRepository;
import com.tqi.pix.pix.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tqi.pix.pix.mapper.PessoaMapper.*;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PixService pixService;

    private final ChavePixRepository chavePixRepository;


    public PessoaComChavePixDTO salvar(PessoaForm pessoaForm) {
        Pessoa pessoa = pessoaRepository.save(formParaEntidade(pessoaForm));
        return entidadeParaPessoaComChaveDto(pessoa);
    }

    public void deletar(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        pessoaRepository.deleteById(id);
    }

    public Pessoa atualizar(Long id, PessoaForm pessoaForm) {
        Pessoa pessoaAtualizada = pessoaRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        pessoaAtualizada.setNome(pessoaForm.getNome());
        pessoaAtualizada.setCpf(pessoaForm.getCpf());
        pessoaAtualizada.setEmail(pessoaForm.getEmail());
        return pessoaRepository.save(pessoaAtualizada);
    }

    public List<PessoaComChavePixDTO> listaDePessoas() {
        var pessoas = pessoaRepository.findAll()
                .stream()
                .map(PessoaMapper::entidadeParaPessoaComChaveDto)
                .toList();
        for (PessoaComChavePixDTO pessoa: pessoas) {
            pessoa.setChavesPix(pixService.recuperarPorIdPessoa(pessoa.getId()));
        }
        return pessoas;
    }

    public PessoaComChavePixDTO detalharPessoaComChavePix(Long id) {
        return pessoaRepository.findById(id)
                .map(PessoaMapper::entidadeParaPessoaComChaveDto)
                .map(pessoaDTO -> {
                    pessoaDTO.setChavesPix(pixService.recuperarPorIdPessoa(pessoaDTO.getId()));
                    return pessoaDTO;
                })
                .orElseThrow(EntityNotFoundException::new);
    }
}



