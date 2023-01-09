package com.tqi.pix.pix.mapper;

import com.tqi.pix.pix.model.dto.PessoaDTO;
import com.tqi.pix.pix.model.Pessoa;
import com.tqi.pix.pix.model.form.PessoaForm;

import java.util.List;


public final class PessoaMapper {

    private PessoaMapper(){

    }

    // Para transformar o form em entidade, para receber o formulario
    public static Pessoa formParaEntidade(PessoaForm pessoaForm) {
        return Pessoa.builder()
                .id(pessoaForm.getId())
                .nome(pessoaForm.getNome())
                .cpf(pessoaForm.getCpf())
                .email(pessoaForm.getEmail())
                .build();
    }

    // Para transformar a entidade em dto
    public static PessoaDTO entidadeParaDto(Pessoa pessoa){
        return PessoaDTO.builder()
                .id(pessoa.getId())
                .nome(pessoa.getNome())
                .build();
    }

    public static List<PessoaDTO> listDeEntidadeParaDto(List<Pessoa> pessoas){
        return pessoas.stream().map(PessoaMapper::entidadeParaDto).toList();
    }
}
