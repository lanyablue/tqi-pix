package com.tqi.pix.pix.impl;

import com.tqi.pix.pix.mapper.PessoaMapper;
import com.tqi.pix.pix.mapper.PixMapper;
import com.tqi.pix.pix.model.ChavePix;
import com.tqi.pix.pix.model.dto.ChavePixCompletaDTO;
import com.tqi.pix.pix.model.dto.ChavePixDTO;
import com.tqi.pix.pix.model.form.ChavePixForm;
import com.tqi.pix.pix.repository.ChavePixRepository;
import com.tqi.pix.pix.repository.PessoaRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.tqi.pix.pix.mapper.PixMapper.entidadeParaDTOCompleto;
import static com.tqi.pix.pix.mapper.PixMapper.formParaEntidade;

@Service
@RequiredArgsConstructor
public class PixService {

    private final ChavePixRepository chavePixRepository;
    private final PessoaRepository pessoaRepository;

    public ChavePixCompletaDTO criarChavePix(ChavePixForm chavePixForm) {
        chaveJaExiste(chavePixForm.getChave());
        pessoaNaoExiste(chavePixForm.getIdPessoa());
        ChavePix chavePix = chavePixRepository.save(formParaEntidade(chavePixForm));
        return setPessoaDTO(chavePix);
    }

    private ChavePixCompletaDTO setPessoaDTO(ChavePix chavePix) {
        var pessoaComChavePixDTO = pessoaRepository.findById(chavePix.getIdPessoa()).map(PessoaMapper::entidadeParaDto).get();
        ChavePixCompletaDTO chavePixCompletaDTO = entidadeParaDTOCompleto(chavePix);
        chavePixCompletaDTO.setPessoa(pessoaComChavePixDTO);
        return chavePixCompletaDTO;
    }

    private void pessoaNaoExiste(Long id) {
        if (!pessoaRepository.existsById(id)){
            throw new EntityNotFoundException("Não existe uma pessoa com este id");
        }
    }

    private void chaveJaExiste(String chave) {
        if (chavePixRepository.existsByChave(chave)){
            throw new EntityExistsException("Essa chave já está cadastrada");
        }
    }

    public ChavePixCompletaDTO recuperarPorChave(String chave) {
        return chavePixRepository.findByChave(chave)
                .map(chavePix ->
                     ChavePixCompletaDTO.builder()
                            .chave(chavePix.getChave())
                            .pessoa(pessoaRepository.findById(chavePix.getIdPessoa()).map(PessoaMapper::entidadeParaDto).get())
                            .build()
                )
                .orElseThrow(EntityExistsException::new);
    }

    public List<ChavePixDTO> recuperarPorIdPessoa(Long id){
        return chavePixRepository.findAllByIdPessoa(id)
                .stream() // percorre a lista de chaves
                .map(PixMapper::entidadeParaDTO) // transformando as chaves para dto
                .toList(); // retornando tudo em formato de lista
    }

    public void deletar(String chave) {
        if (!chavePixRepository.existsById(chave)) {
            throw new EntityNotFoundException();
        }
        chavePixRepository.deleteById(chave);
    }

    public List<ChavePixCompletaDTO> recuperarChaves() {
        return chavePixRepository.findAll()
                .stream()
                .map(PixMapper::entidadeParaDTOCompleto)
                .toList();
    }

    public ChavePixCompletaDTO atualizar(ChavePixForm chavePixForm) {
        ChavePix chavePix = chavePixRepository.findById(chavePixForm.getChave())
                .orElseThrow(EntityNotFoundException::new);
        if (Objects.nonNull(chavePixForm.getIdPessoa())){
            pessoaNaoExiste(chavePixForm.getIdPessoa());
            chavePix.setIdPessoa(chavePixForm.getIdPessoa());
        }
        if (Objects.nonNull(chavePixForm.getContaCorrente())) chavePix.setContaCorrente(chavePixForm.getContaCorrente());
        if (Objects.nonNull(chavePixForm.getAgencia())) chavePix.setAgencia(chavePixForm.getAgencia());
        if (Objects.nonNull(chavePixForm.getBanco())) chavePix.setBanco(chavePixForm.getBanco());
        return setPessoaDTO(chavePixRepository.save(chavePix));
    }
}
