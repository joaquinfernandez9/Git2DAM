package com.example.graphqlserver.domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coche {
    private String id;
    private String marca;

    private List<Rueda> ruedas;

    public Coche(String id) {
        this.id = id;
    }
}
