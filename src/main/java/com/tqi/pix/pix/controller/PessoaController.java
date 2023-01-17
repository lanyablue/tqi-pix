package com.tqi.pix.pix.controller;

import com.tqi.pix.pix.impl.PessoaService;
import com.tqi.pix.pix.mapper.PessoaMapper;
import com.tqi.pix.pix.model.dto.PessoaComChavePixDTO;
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
    public PessoaComChavePixDTO salvarPessoa(@RequestBody PessoaForm pessoaForm) {
        return pessoaService.salvar(pessoaForm);
    }

    @DeleteMapping("/{id}")
    public void deletarPessoa(@PathVariable Long id) {
        pessoaService.deletar(id);
    }

    @PutMapping("/{id}")
    public PessoaComChavePixDTO atualizarPessoa(@PathVariable Long id,
                                                                @RequestBody PessoaForm pessoaForm) {
        return PessoaMapper.entidadeParaPessoaComChaveDto(pessoaService.atualizar(id, pessoaForm));
    }

    @GetMapping
    public List<PessoaComChavePixDTO> listarTodasAsPessoas() {
        return pessoaService.listaDePessoas();
    }

    @GetMapping("/{id}")
    public PessoaComChavePixDTO detalharPessoa(@PathVariable Long id) {
        return pessoaService.detalharPessoaComChavePix(id);
    }
}
