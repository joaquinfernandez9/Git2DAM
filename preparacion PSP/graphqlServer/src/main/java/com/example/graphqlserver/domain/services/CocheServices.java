package com.example.graphqlserver.domain.services;

import com.example.graphqlserver.controller.exception.CocheNotFoundException;
import com.example.graphqlserver.controller.exception.MaximoRuedasException;
import com.example.graphqlserver.data.repos.CocheRepository;
import com.example.graphqlserver.data.repos.RuedaRepository;
import com.example.graphqlserver.domain.modelo.Coche;
import com.example.graphqlserver.domain.modelo.Rueda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CocheServices {

    private final CocheRepository cocheRepository;
    private final RuedaRepository ruedaRepository;


    public CocheServices(CocheRepository cocheRepository, RuedaRepository ruedaRepository) {
        this.cocheRepository = cocheRepository;
        this.ruedaRepository = ruedaRepository;
    }

    //read all
    public List<Coche> getAll(){
        return cocheRepository.findAll();
    }

    //read by id
    public Coche get(String coche){
        return cocheRepository.findById(coche).orElse(null);
    }

    //add
    public Coche save(Coche coche){
        return cocheRepository.save(coche);
    }

    //delete
    public void delete(String idCoche){
        Coche coche = new Coche(idCoche);
        cocheRepository.delete(coche);
    }

    //add rueda a coche
    public Coche agregarRueda(String idCoche, Rueda rueda){
        Coche coche = cocheRepository.findById(idCoche).orElse(null);
        if (coche != null){
            List<Rueda> ruedas = coche.getRuedas();
            if (ruedas.size()>=4){
                throw new MaximoRuedasException("el coche ya tiene 4 ruedas, prueba en otro");
            } else {
                ruedas.add(rueda);
                coche.setRuedas(ruedas);
                ruedaRepository.save(rueda);
                cocheRepository.save(coche);
                return coche;
            }
        } else {
            throw new CocheNotFoundException("No se ha encontrado el coche a partir del id proporcionado");
        }


    }

}
