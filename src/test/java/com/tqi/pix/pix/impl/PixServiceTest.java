package com.tqi.pix.pix.impl;

import com.tqi.pix.pix.model.ChavePix;
import com.tqi.pix.pix.model.Pessoa;
import com.tqi.pix.pix.model.form.ChavePixForm;
import com.tqi.pix.pix.repository.ChavePixRepository;
import com.tqi.pix.pix.repository.PessoaRepository;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class PixServiceTest {

    @InjectMocks
    private PixService pixService;

    @Mock
    private ChavePixRepository chavePixRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @Test
    void testCriarChavePix() {
        //given
        final var pessoa = Pessoa.builder()
                .id(1l)
                .cpf("123456")
                .email("asdasd@gmail.com")
                .nome("Fulado")
                .build();

        final var chaveParaSalvar = ChavePix.builder()
                .chave("123456")
                .agencia("123456-7")
                .banco("Nubank")
                .contaCorrente("123456")
                .idPessoa(1L)
                .build();

        final var chavepixForm = ChavePixForm.builder()
                .chave("123456")
                .agencia("123456-7")
                .banco("Nubank")
                .contaCorrente("123456")
                .idPessoa(1L)
                .build();

        //when
        when(pessoaRepository.existsById(anyLong())).thenReturn(true);
        when(pessoaRepository.findById(anyLong())).thenReturn(Optional.of(pessoa));
        when(chavePixRepository.save(any(ChavePix.class))).thenReturn(chaveParaSalvar);

        final var retorno = pixService.criarChavePix(chavepixForm);

        //then
        assertNotNull(retorno);
        assertEquals(chaveParaSalvar.getChave(), retorno.getChave());

    }


}
