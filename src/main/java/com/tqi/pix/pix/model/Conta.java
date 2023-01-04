package com.tqi.pix.pix.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int contaCorrente;
    private int agencia;
    @ManyToOne
    private Pessoa dono;
    @Enumerated(EnumType.STRING)
    private Bancos banco;

}
