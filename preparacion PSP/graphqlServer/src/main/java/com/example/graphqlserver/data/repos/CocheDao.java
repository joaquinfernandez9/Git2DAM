package com.example.graphqlserver.data.repos;

import com.example.graphqlserver.domain.modelo.Coche;

import java.util.ArrayList;
import java.util.List;

public class CocheDao {
    private final List<Coche> cocheList;

    public CocheDao(List<Coche> cocheList) {
        this.cocheList = cocheList;
    }

    public List<Coche> getCocheList() {
        return new ArrayList<>(cocheList);
    }

    public void addCoche(Coche coche) {
        cocheList.add(coche);
    }

    public Coche getCoche(String id) {
        return cocheList.stream()
          .filter(coche -> id.equals(coche.getId()))
          .findFirst()
          .orElseThrow(RuntimeException::new);
    }

}
