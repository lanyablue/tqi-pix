package com.tqi.pix.pix.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ChavePixCompletaDTO {
    private String chave;
    private PessoaDTO pessoa;
    private String contaCorrente;
    private String agencia;
    private String banco;

}
