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

    @GetMapping("/{idPessoa}")
    public List<ChavePixDTO> recuperarChavesPorIdPessoa(@PathVariable Long idPessoa) {
        return pixService.detalharChavePixPeloIdDono(idPessoa);
    }

    @GetMapping
    public ChavePixCompletaDTO recuperarChave(@PathParam("chave") String chave) {
        return pixService.detalharChavePixPelaChave(chave);
    }


}
