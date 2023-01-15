package com.tqi.pix.pix.mapper;

import com.tqi.pix.pix.model.ChavePix;
import com.tqi.pix.pix.model.dto.ChavePixCompletaDTO;
import com.tqi.pix.pix.model.dto.ChavePixDTO;
import com.tqi.pix.pix.model.form.ChavePixForm;

public class PixMapper {

    private PixMapper() {

    }

    public static ChavePix formParaEntidade(ChavePixForm chavePixForm) {
        return ChavePix.builder()
                .chave(chavePixForm.getChave())
                .idPessoa(chavePixForm.getIdPessoa())

                .build();
    }

    public static ChavePixDTO entidadeParaDTO(ChavePix chavePix) {
        return ChavePixDTO.builder()
                .chave(chavePix.getChave())
                .build();
    }

    public static ChavePixCompletaDTO entidadeParaDTOCompleto(ChavePix chavePix){
        return ChavePixCompletaDTO.builder()
                .chave(chavePix.getChave())
                .contaCorrente(chavePix.getContaCorrente())
                .agencia(chavePix.getAgencia())
                .banco(chavePix.getBanco())
                .build();
    }


}
