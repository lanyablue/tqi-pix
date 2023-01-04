package com.tqi.pix.pix.model.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PessoaForm {

    private Long id;
    private String nome;
    private String cpf;
    private String email;


}
