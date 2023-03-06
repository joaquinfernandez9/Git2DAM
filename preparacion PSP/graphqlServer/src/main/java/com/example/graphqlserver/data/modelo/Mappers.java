package com.example.graphqlserver.data.modelo;

import com.example.graphqlserver.domain.modelo.Coche;
import com.example.graphqlserver.domain.modelo.Rueda;
import org.springframework.stereotype.Component;

@Component
public class Mappers {
    private final RuedaMapper mapper;

    public Mappers(RuedaMapper mapper) {
        this.mapper = mapper;
    }

    public Coche toCoche(CocheEntity entity) {
        Coche coche = new Coche();
        coche.setId(entity.getId());
        coche.setMarca(entity.getMarca());
        coche.setRuedas(null);
        return coche;
    }

    public CocheEntity toCocheEntity(Coche coche) {
        CocheEntity cocheEntity = new CocheEntity();
        cocheEntity.setId(coche.getId());
        cocheEntity.setMarca(coche.getMarca());
        cocheEntity.setRuedas(null);
        return cocheEntity;
    }

    public Coche toCocheConRuedas(CocheEntity cocheEntity) {
        Coche coche = new Coche();
        coche.setId(coche.getId());
        coche.setMarca(coche.getMarca());
        coche.setRuedas(cocheEntity.getRuedas().stream().map(mapper::toRueda).toList());
        return coche;
    }

}
