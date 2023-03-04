package com.example.graphqlserver.data.repos;

import com.example.graphqlserver.controller.CocheController;
import com.example.graphqlserver.domain.modelo.Coche;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Retention;

@Repository
public interface CocheRepository extends ListCrudRepository<Coche, String> {

    //get all coches, add coche, borrar coche

}
