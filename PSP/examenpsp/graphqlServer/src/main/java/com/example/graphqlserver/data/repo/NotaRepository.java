package com.example.graphqlserver.data.repo;

import com.example.graphqlserver.data.model.NotaEntity;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends ListCrudRepository<NotaEntity, String> {
}
