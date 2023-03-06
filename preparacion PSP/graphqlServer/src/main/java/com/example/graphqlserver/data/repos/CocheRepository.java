package com.example.graphqlserver.data.repos;

import com.example.graphqlserver.data.modelo.CocheEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocheRepository extends ListCrudRepository<CocheEntity, String> {

}
