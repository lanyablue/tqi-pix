package com.tqi.pix.pix.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tqi.pix.pix.impl.PixService;
import com.tqi.pix.pix.model.Pessoa;
import com.tqi.pix.pix.model.form.ChavePixForm;
import com.tqi.pix.pix.model.form.PessoaForm;
import com.tqi.pix.pix.repository.ChavePixRepository;
import com.tqi.pix.pix.repository.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PessoaController.class)
public class PessoaControllerTest {

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

//    @Test
//    void testSalvarPessoa() throws Exception {
//        when(pessoaRepository.existsById(anyLong())).thenReturn(true);
//        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(new Pessoa()));
//
//        PessoaForm pessoaForm = PessoaForm.builder()
//                .id(1l)
//                .nome("Albert")
//                .cpf("123456789")
//                .email("email@email.com")
//                .build();
//
//        mockMvc.perform(post("/pessoa")
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(pessoaForm)))
//                .andExpect(status().isOk());
//    }



}
