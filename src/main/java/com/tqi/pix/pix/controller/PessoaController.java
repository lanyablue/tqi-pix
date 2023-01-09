package com.tqi.pix.pix.controller;

import com.tqi.pix.pix.impl.PessoaService;
import com.tqi.pix.pix.mapper.PessoaMapper;
import com.tqi.pix.pix.model.dto.PessoaDTO;
import com.tqi.pix.pix.model.form.PessoaForm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")   //rota
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaDTO> salvarPessoa(@RequestBody PessoaForm pessoaForm){
        return new ResponseEntity<>(PessoaMapper.entidadeParaDto(pessoaService.salvar(pessoaForm)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PessoaDTO> deletarPessoa(@PathVariable Long id){
        try {
            pessoaService.deletar(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException noSuchElementException) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long id,
                                                     @RequestBody PessoaForm pessoaForm){
        return new ResponseEntity<>(PessoaMapper.entidadeParaDto(pessoaService.atualizar(id, pessoaForm)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listarTodasAsPessoas (){
        return new ResponseEntity<>(pessoaService.listaDePessoas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> detalharPessoa (@PathVariable Long id) {
        return new ResponseEntity<>(pessoaService.detalharPessoa(id), HttpStatus.OK);
    }



}
