package com.example.graphqlserver.data.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "alumno")
public class AlumnoEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    private List<NotaEntity> notas;
}
