package com.example.graphqlserver.data.modelo;

import com.example.graphqlserver.domain.modelo.Rueda;
import org.springframework.stereotype.Component;

@Component
public class RuedaMapper {

    public RuedaEntity toRuedaEntity(Rueda rueda, CocheEntity coche){
        RuedaEntity entity = new RuedaEntity();
        entity.setNombre(rueda.getNombre());
        entity.setPulgadas(rueda.getPulgadas());
        entity.setCoche(coche);
        return entity;
    }

    public Rueda toRueda(RuedaEntity entity){
        Rueda rueda = new Rueda();
        rueda.setNombre(entity.getNombre());
        rueda.setPulgadas(entity.getPulgadas());
        rueda.setId_coche(entity.getCoche().getId());
        return rueda;
    }
}
