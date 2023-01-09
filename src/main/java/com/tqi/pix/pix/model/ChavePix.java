package com.tqi.pix.pix.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class ChavePix {

    @Id
    private String chave;
    @ManyToOne
    private Pessoa dono;
    @ManyToOne
    private Conta contaBancaria;

}
