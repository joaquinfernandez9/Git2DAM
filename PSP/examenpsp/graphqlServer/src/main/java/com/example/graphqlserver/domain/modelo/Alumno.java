package com.example.graphqlserver.domain.modelo;

import lombok.Data;

import java.util.List;


@Data
public class Alumno {
    private int id;
    private String nombre;

    private List<Nota> notas;

    public Alumno(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Alumno() {
    }

    public Alumno(String nombre) {
        this.nombre = nombre;
    }
}
