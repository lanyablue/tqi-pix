package com.tqi.pix.pix.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PessoaComChavePixDTO implements Serializable {

    private Long id;
    private String nome;
    private List<ChavePixDTO> chavesPix;


}
