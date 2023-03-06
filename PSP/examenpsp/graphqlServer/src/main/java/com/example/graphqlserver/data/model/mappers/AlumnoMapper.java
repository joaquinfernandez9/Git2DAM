package com.example.graphqlserver.data.model.mappers;

import com.example.graphqlserver.data.model.AlumnoEntity;
import com.example.graphqlserver.domain.modelo.Alumno;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AlumnoMapper {

    private final NotasMapper notasMapper;

    public AlumnoMapper(NotasMapper notasMapper) {
        this.notasMapper = notasMapper;
    }

    public Alumno toAlumno(AlumnoEntity alumnoEntity) {
        Alumno alumno = new Alumno();
        alumno.setId(alumnoEntity.getId());
        alumno.setNombre(alumnoEntity.getNombre());
        alumno.setNotas(null);
        return alumno;
    }

    public Alumno toAlumnoConNotas(AlumnoEntity alumnoEntity) {
        Alumno alumno = new Alumno();
        alumno.setId(alumnoEntity.getId());
        alumno.setNombre(alumnoEntity.getNombre());
        alumno.setNotas(alumnoEntity.getNotas().stream().map(notasMapper::toNotas).toList());
        return alumno;
    }


    public AlumnoEntity toAlumnoEntity(Alumno alumno) {
        AlumnoEntity alumnoEntity = new AlumnoEntity();
        alumnoEntity.setId(alumno.getId());
        alumnoEntity.setNombre(alumno.getNombre());
        alumnoEntity.setNotas(Collections.emptyList());
        return alumnoEntity;
    }



}
