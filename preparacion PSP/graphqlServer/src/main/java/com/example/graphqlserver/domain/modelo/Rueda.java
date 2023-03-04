package com.example.graphqlserver.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rueda {
    private String nombre;
    private int pulgadas;

    private String id_coche;
}
