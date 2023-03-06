package com.example.graphqlserver.spring.controller;

import com.example.graphqlserver.domain.modelo.Alumno;
import com.example.graphqlserver.domain.services.AlumnoServices;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AlumnoController
{

    private final AlumnoServices alumnoServices;

    public AlumnoController(AlumnoServices alumnoServices) {
        this.alumnoServices = alumnoServices;
    }


    @QueryMapping
    public List<Alumno> getAllAlumnos() {
        return alumnoServices.getAll();
    }


    @MutationMapping
    public Alumno addAlumno(@Argument String nombre) {
        Alumno alumno = new Alumno(nombre);
        return alumnoServices.add(alumno);
    }

}
