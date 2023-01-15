package com.tqi.pix.pix.controller;

import com.tqi.pix.pix.impl.PixService;
import com.tqi.pix.pix.model.dto.ChavePixCompletaDTO;
import com.tqi.pix.pix.model.dto.ChavePixDTO;
import com.tqi.pix.pix.model.form.ChavePixForm;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pix")   //rota
public class PixController {


    private final PixService pixService;

    @PostMapping
    public ChavePixCompletaDTO criarChavePix(@RequestBody ChavePixForm chavePixForm) {
        return pixService.criarChavePix(chavePixForm);
    }

    @GetMapping("/pessoa")
    public List<ChavePixDTO> recuperarChavesPorIdPessoa(@PathParam("idPessoa") Long idPessoa) {
        return pixService.recuperarPorIdPessoa(idPessoa);
    }

    @GetMapping("/{chave}")
    public ChavePixCompletaDTO recuperarChave(@PathVariable String chave) {
        return pixService.recuperarPorChave(chave);
    }

    @GetMapping
    public List<ChavePixCompletaDTO> recuperarChaves() {
        return pixService.recuperarChaves();
    }

    @DeleteMapping("/{chave}")
    public void deletarChave(@PathVariable String chave) {
        pixService.deletar(chave);
    }

    @PatchMapping
    public ChavePixCompletaDTO atualizar(@RequestBody ChavePixForm chavePixForm){
        return pixService.atualizar(chavePixForm);
    }
}
