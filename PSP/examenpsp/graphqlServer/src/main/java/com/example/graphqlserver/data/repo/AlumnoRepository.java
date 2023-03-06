package com.example.graphqlserver.data.repo;

import com.example.graphqlserver.data.model.AlumnoEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepository extends ListCrudRepository<AlumnoEntity, String> {
}
