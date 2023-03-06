package com.example.graphqlserver.data.model.mappers;

import com.example.graphqlserver.data.model.NotaEntity;
import com.example.graphqlserver.domain.modelo.Nota;
import org.springframework.stereotype.Component;

@Component
public class NotasMapper {


    public Nota toNotas(NotaEntity notasEntity) {
        Nota notas = new Nota();
        notas.setId(notasEntity.getId());
        notas.setNombre(notasEntity.getNombre());
        notas.setNota(notasEntity.getNota());
        notas.setId_alumno(notasEntity.getId_alumno());
        return notas;
    }

    public NotaEntity toNotasEntity(Nota notas) {
        NotaEntity notasEntity = new NotaEntity();
        notasEntity.setId(notas.getId());
        notasEntity.setNota(notas.getNota());
        notasEntity.setAlumno(null);
        return notasEntity;
    }
}
