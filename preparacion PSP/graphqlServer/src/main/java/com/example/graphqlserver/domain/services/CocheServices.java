package com.example.graphqlserver.domain.services;

import com.example.graphqlserver.data.repos.CocheDao;
import com.example.graphqlserver.spring.errors.CocheNotFoundException;
import com.example.graphqlserver.spring.errors.MaximoRuedasException;
import com.example.graphqlserver.data.modelo.CocheEntity;
import com.example.graphqlserver.data.modelo.Mappers;
import com.example.graphqlserver.data.modelo.RuedaEntity;
import com.example.graphqlserver.data.modelo.RuedaMapper;
import com.example.graphqlserver.data.repos.CocheRepository;
import com.example.graphqlserver.data.repos.RuedaRepository;
import com.example.graphqlserver.domain.modelo.Coche;
import com.example.graphqlserver.domain.modelo.Rueda;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CocheServices {

    private final CocheDao cocheRepository;
    private final RuedaRepository ruedaRepository;
    private final Mappers mapper;
    private final RuedaMapper ruedaMapper;

    public CocheServices(CocheDao cocheRepository, RuedaRepository ruedaRepository, Mappers mapper, RuedaMapper ruedaMapper) {
        this.cocheRepository = cocheRepository;
        this.ruedaRepository = ruedaRepository;
        this.mapper = mapper;
        this.ruedaMapper = ruedaMapper;
    }


    //read all
    public List<Coche> getAll(){
//        return cocheRepository.findAll().stream().map(mapper::toCocheConRuedas).toList();
        return cocheRepository.getCocheList();
    }

    //read by id
    public Coche get(String coche){
//        return cocheRepository.findById(coche).map(mapper::toCoche).orElse(null);
        return cocheRepository.getCoche(coche);
    }

    //add
    public Coche save(Coche coche){
//        CocheEntity entity = mapper.toCocheEntity(coche);
        //entity =
        cocheRepository.addCoche(coche);
        return coche;
//        return mapper.toCoche(entity);
    }

    //delete
//    public void delete(String idCoche){
//        CocheEntity coche = cocheRepository.findById(idCoche).orElse(null);
//        cocheRepository.delete(coche);
//    }
//
//    public Coche agregarRueda(String idCoche, Rueda rueda){
//        CocheEntity cocheEntity = cocheRepository.findById(idCoche).orElse(null);
//        if (cocheEntity != null){
//            Coche coche = mapper.toCocheConRuedas(cocheEntity);
//            List<Rueda> ruedas = coche.getRuedas();
//            if (ruedas.size()>=4){
//                throw new MaximoRuedasException("el coche ya tiene 4 ruedas, prueba en otro");
//            } else {
//                ruedas.add(rueda);
//                List<RuedaEntity> ruedaEntities = new java.util.ArrayList<>(Collections.emptyList());
//                ruedas.forEach(rueda1 -> ruedaEntities.add(ruedaMapper.toRuedaEntity(rueda1,cocheEntity)));
//                cocheEntity.setRuedas(ruedaEntities);
//                ruedaRepository.save(ruedaMapper.toRuedaEntity(rueda, cocheEntity));
//                cocheRepository.save(cocheEntity);
//                return coche;
//            }
//        } else {
//            throw new CocheNotFoundException("No se ha encontrado el coche a partir del id proporcionado");
//        }
//
//
//    }

}
