package com.tqi.pix.pix.model.dto;

import com.tqi.pix.pix.model.ChavePix;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PessoaDTO {

    private Long id;
    private String nome;
    private List<ChavePix> chavesPix;


}
