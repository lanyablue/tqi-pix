package com.tqi.pix.pix.impl;

import com.tqi.pix.pix.mapper.PessoaMapper;
import com.tqi.pix.pix.model.Pessoa;
import com.tqi.pix.pix.model.dto.PessoaDTO;
import com.tqi.pix.pix.model.form.PessoaForm;
import com.tqi.pix.pix.repository.ChavePixRepository;
import com.tqi.pix.pix.repository.PessoaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tqi.pix.pix.mapper.PessoaMapper.*;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository pessoaRepository;
    private final PixService pixService;

    private final ChavePixRepository chavePixRepository;


    public PessoaDTO salvar(PessoaForm pessoaForm) {
        Pessoa pessoa = pessoaRepository.save(formParaEntidade(pessoaForm));
        return entidadeParaDto(pessoa);
    }

    public void deletar(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        pessoaRepository.deleteById(id);
    }

    public Pessoa atualizar(Long id, PessoaForm pessoaForm) {
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

    public List<PessoaDTO> listaDePessoas() {
        var pessoas = pessoaRepository.findAll().stream().map(PessoaMapper::entidadeParaDto).toList();

        for (PessoaDTO pessoa: pessoas) {
            pessoa.setChavesPix(pixService.detalharChavePixPeloIdDono(pessoa.getId()));
        }

        return pessoas;
    }

    public PessoaDTO detalharPessoaComChavePix(Long id) {
        return pessoaRepository.findById(id)
                .map(PessoaMapper::entidadeParaDto)
                .map(pessoaDTO -> {
                    pessoaDTO.setChavesPix(pixService.detalharChavePixPeloIdDono(pessoaDTO.getId()));
                    return pessoaDTO;
                })
                .orElseThrow(EntityNotFoundException::new);
    }
}



