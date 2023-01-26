package com.tqi.pix.pix.model.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChavePixForm {

    private String chave;
    private Long idPessoa;
    private String contaCorrente;
    private String agencia;
    private String banco;

}
