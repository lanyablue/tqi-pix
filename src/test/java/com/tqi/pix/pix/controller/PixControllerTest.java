package com.tqi.pix.pix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tqi.pix.pix.impl.PixService;
import com.tqi.pix.pix.model.ChavePix;
import com.tqi.pix.pix.model.Pessoa;
import com.tqi.pix.pix.model.form.ChavePixForm;
import com.tqi.pix.pix.repository.ChavePixRepository;
import com.tqi.pix.pix.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PixController.class)
public class PixControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private PixService pixService;
    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private ChavePixRepository chavePixRepository;

    @Test
    void testCriarChavePix() throws Exception {
        when(pessoaRepository.existsById(anyLong())).thenReturn(true);
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(new Pessoa()));

        ChavePixForm chavePixForm = ChavePixForm.builder()
                .chave("123456")
                .agencia("123456-7")
                .banco("Nubank")
                .contaCorrente("123456")
                .idPessoa(1L)
                .build();

        mockMvc.perform(post("/pix")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(chavePixForm)))
                .andExpect(status().isOk());
    }

    @Test
    void testRecuperarChavesPorIdPessoa() throws Exception {
        when(pessoaRepository.existsById(anyLong())).thenReturn(true);
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(new Pessoa()));

        Long idPessoa = 1L;

        mockMvc.perform(get("/pix")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(idPessoa)))
                .andExpect(status().isOk());
    }

    @Test
    void testRecuperarChave() throws Exception {
        when(pessoaRepository.existsById(anyLong())).thenReturn(true);
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(new Pessoa()));

        String chavePix = "123456";

        mockMvc.perform(get("/pix")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(chavePix)))
                .andExpect(status().isOk());
    }

    @Test
    void testRecuperarChaves() throws Exception {
        when(pessoaRepository.existsById(anyLong())).thenReturn(true);
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(new Pessoa()));

        mockMvc.perform(get("/pix")
                        .contentType("application/json")
                        .content(objectMapper.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void testDeletarChave() throws Exception {
        when(pessoaRepository.existsById(anyLong())).thenReturn(true);
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(new Pessoa()));

        ChavePix chave = ChavePix.builder()
                .chave("123456")
                .idPessoa(1L)
                .banco("Nubank")
                .contaCorrente("1111")
                .agencia("0001")
                .build();

        chavePixRepository.save(chave);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/pix/123456")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testAtualizar() throws Exception {

        ChavePix chave = ChavePix.builder()
                .chave("123456")
                .idPessoa(1L)
                .banco("Nubank")
                .contaCorrente("1111")
                .agencia("0001")
                .build();

        Mockito.when(chavePixRepository.findById(chave.getChave())).thenReturn(Optional.of(chave));
        Mockito.when(chavePixRepository.save(chave)).thenReturn(chave);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/pix")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(chave));
    }


}
