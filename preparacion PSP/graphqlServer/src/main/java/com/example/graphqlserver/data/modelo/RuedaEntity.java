package com.example.graphqlserver.data.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rueda")
public class RuedaEntity {
    @Id
    private String nombre;
    private int pulgadas;

    @ManyToOne
    @JoinColumn(name = "id_coche", referencedColumnName = "id")
    private CocheEntity id_coche;
}
