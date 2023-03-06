package com.example.graphqlserver.data.repos;

import com.example.graphqlserver.data.modelo.RuedaEntity;
import com.example.graphqlserver.domain.modelo.Coche;
import com.example.graphqlserver.domain.modelo.Rueda;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuedaRepository  extends ListCrudRepository<RuedaEntity, String> {

}
