package com.tqi.pix.pix.controller;

import com.tqi.pix.pix.impl.PessoaService;
import com.tqi.pix.pix.mapper.PessoaMapper;
import com.tqi.pix.pix.model.dto.PessoaDTO;
import com.tqi.pix.pix.model.form.PessoaForm;
import com.tqi.pix.pix.repository.PessoaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")   //rota
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaDTO> salvarPessoa(@RequestBody PessoaForm pessoaForm){
        return new ResponseEntity<>(PessoaMapper.entidadeParaDto(pessoaService.salvar(pessoaForm)), HttpStatus.OK);
    }




}
