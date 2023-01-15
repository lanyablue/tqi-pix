package com.tqi.pix.pix.impl;

import com.tqi.pix.pix.mapper.PessoaMapper;
import com.tqi.pix.pix.mapper.PixMapper;
import com.tqi.pix.pix.model.ChavePix;
import com.tqi.pix.pix.model.dto.ChavePixCompletaDTO;
import com.tqi.pix.pix.model.dto.ChavePixDTO;
import com.tqi.pix.pix.model.dto.PessoaDTO;
import com.tqi.pix.pix.model.form.ChavePixForm;
import com.tqi.pix.pix.repository.ChavePixRepository;
import com.tqi.pix.pix.repository.PessoaRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tqi.pix.pix.mapper.PixMapper.entidadeParaDTOCompleto;
import static com.tqi.pix.pix.mapper.PixMapper.formParaEntidade;

@Service
@RequiredArgsConstructor
public class PixService {

    private final ChavePixRepository chavePixRepository;
    private final PessoaRepository pessoaRepository;

    public ChavePixCompletaDTO criarChavePix(ChavePixForm chavePixForm) {
        if (chavePixRepository.existsByChave(chavePixForm.getChave())){
            throw new EntityExistsException("Essa chave já está cadastrada");
        }
        if (pessoaRepository.existsById(chavePixForm.getIdPessoa())){
            throw new EntityNotFoundException("Não existe uma pessoa com este id");
        }
        ChavePix chavePix = chavePixRepository.save(formParaEntidade(chavePixForm));
        PessoaDTO pessoaDTO = pessoaRepository.findById(chavePix.getIdPessoa()).map(PessoaMapper::entidadeParaDto).get();
        ChavePixCompletaDTO chavePixCompletaDTO = entidadeParaDTOCompleto(chavePix);
        chavePixCompletaDTO.setPessoa(pessoaDTO);
        return chavePixCompletaDTO;
    }

    public ChavePixCompletaDTO detalharChavePixPelaChave(String chave) {
        return chavePixRepository.findByChave(chave)
                .map(chavePix ->
                     ChavePixCompletaDTO.builder()
                            .chave(chavePix.getChave())
                            .pessoa(pessoaRepository.findById(chavePix.getIdPessoa()).map(PessoaMapper::entidadeParaDto).get())
                            .build()
                )
                .orElseThrow(EntityExistsException::new);
    }

    public List<ChavePixDTO> detalharChavePixPeloIdDono(Long id){
        return chavePixRepository.findAllByIdPessoa(id).stream().map(PixMapper::entidadeParaDTO).toList();
    }


}
