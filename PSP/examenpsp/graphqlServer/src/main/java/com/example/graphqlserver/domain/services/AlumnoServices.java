package com.example.graphqlserver.domain.services;

import com.example.graphqlserver.data.model.mappers.AlumnoMapper;
import com.example.graphqlserver.data.repo.AlumnoRepository;
import com.example.graphqlserver.domain.modelo.Alumno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlumnoServices {

    private final AlumnoRepository alumnoRepository;
    private final AlumnoMapper alumnoMapper;

    public AlumnoServices(AlumnoRepository alumnoRepository, AlumnoMapper alumnoMapper) {
        this.alumnoRepository = alumnoRepository;
        this.alumnoMapper = alumnoMapper;
    }

    //get de todos los alumnos
    // alumnoEntity -> alumno
    public List<Alumno> getAll() {
        return alumnoRepository.findAll().stream().map(alumnoMapper::toAlumnoConNotas).toList();
    }

    public Alumno add(Alumno alumno) {
        return alumnoMapper.toAlumno(alumnoRepository.save(alumnoMapper.toAlumnoEntity(alumno)));
    }

}
