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

@RestController
@RequiredArgsConstructor
@RequestMapping("/pessoa")   //rota
public class PessoaController {

    private final PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaDTO> salvarPessoa(@RequestBody PessoaForm pessoaForm) {
        return new ResponseEntity<>(pessoaService.salvar(pessoaForm), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarPessoa(@PathVariable Long id) {
        pessoaService.deletar(id);
        return ResponseEntity.ok("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaDTO> atualizarPessoa(@PathVariable Long id,
                                                     @RequestBody PessoaForm pessoaForm) {
        return new ResponseEntity<>(PessoaMapper.entidadeParaDto(pessoaService.atualizar(id, pessoaForm)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PessoaDTO>> listarTodasAsPessoas() {
        return new ResponseEntity<>(pessoaService.listaDePessoas(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaDTO> detalharPessoa(@PathVariable Long id) {
        return new ResponseEntity<>(pessoaService.detalharPessoaComChavePix(id), HttpStatus.OK);
    }


}
