package com.tqi.pix.pix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
@ToString
public class ChavePix {

    @Id
    private String chave;
    private Long idPessoa;
    private String contaCorrente;
    private String agencia;
    private String banco;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ChavePix chavePix = (ChavePix) o;
        return chave != null && Objects.equals(chave, chavePix.chave);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
