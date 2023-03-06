package com.example.graphqlserver.data.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "nota")
public class NotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nota")
    private int nota;
    @Column(name = "id_alumno", insertable = false, updatable = false)
    private int id_alumno;
    @ManyToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "id")
    private AlumnoEntity alumno;
}
