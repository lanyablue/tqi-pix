package com.tqi.pix.pix.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public enum Bancos {

    NUBANK,
    BANCO_DO_BRASIL,
    SATANDER,
    ITAU,
    BRADESCO;
}
