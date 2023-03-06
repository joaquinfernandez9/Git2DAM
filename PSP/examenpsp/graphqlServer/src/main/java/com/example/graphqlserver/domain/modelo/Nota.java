package com.example.graphqlserver.domain.modelo;

import lombok.Data;

@Data
public class Nota {
    private int id;
    private String nombre;
    private int nota;
    private int id_alumno;
}
