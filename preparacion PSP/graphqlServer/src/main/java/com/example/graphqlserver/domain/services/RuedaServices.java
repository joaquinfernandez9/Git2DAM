package com.example.graphqlserver.domain.services;

import com.example.graphqlserver.data.repos.RuedaRepository;
import com.example.graphqlserver.domain.modelo.Rueda;
import org.springframework.stereotype.Service;

@Service
public class RuedaServices {

    private RuedaRepository repo;

    public RuedaServices(RuedaRepository repo) {
        this.repo = repo;
    }

    //delete rueda
    public void deleteRueda(Rueda rueda){
        repo.delete(rueda);
    }
}
