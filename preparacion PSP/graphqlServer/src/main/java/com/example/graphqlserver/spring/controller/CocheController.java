package com.example.graphqlserver.spring.controller;

import com.example.graphqlserver.domain.modelo.Coche;
import com.example.graphqlserver.domain.modelo.Rueda;
import com.example.graphqlserver.domain.services.CocheServices;
import org.springframework.data.jpa.repository.Query;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Collections;
import java.util.List;

@Controller
public class CocheController {
    private CocheServices serv;
    public CocheController(CocheServices serv) {
        this.serv = serv;
    }
    @QueryMapping
    public List<Coche> getAllCoches(){
        return serv.getAll();
    }
    @QueryMapping
    public Coche getCoche(@Argument String idCoche){
        return serv.get(idCoche);
    }
    @MutationMapping
    public Coche postCoche(@Argument String id, @Argument String marca){
        Coche coche = new Coche(id, marca, Collections.emptyList());
        return serv.save(coche);
    }
//    @MutationMapping
//    public void delete(@Argument String idCoche){
//        serv.delete(idCoche);
//    }
//    @MutationMapping
//    public Coche addRueda(@Argument String id, @Argument String nombre, @Argument int pulgadas){
//        Rueda rueda = new Rueda(nombre, pulgadas, id);
//        return serv.agregarRueda(id, rueda);
//    }
}
