package dao.impl;

import dao.DaoSeries;
import dao.DataBase;
import domain.modelo.Actor;
import domain.modelo.Capitulo;
import domain.modelo.Serie;
import jakarta.inject.Inject;

import java.util.List;

public class DaoSeriesImpl implements DaoSeries {
    private final DataBase dataBase;

    @Inject
    public DaoSeriesImpl(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public boolean añadirSerie(Serie serie){
        List<Serie> series = dataBase.loadSeries();
        series.add(serie);
        return dataBase.saveSeries(series);
    }

    @Override
    public List<Serie> verSeries(){
        return dataBase.loadSeries();
    }

    @Override
    public boolean añadirActor(String nombreSerie, String nombreCapitulo, String nombreActor){
        List<Serie> series = dataBase.loadSeries();
        Serie s =  series.stream().filter(serie -> serie.getNombre().equals(nombreSerie))
                .findFirst().orElse(null);
        if (s!=null){
            Capitulo c = s.getEpisodios().stream().filter(capitulo -> capitulo.getNombre().equals(nombreCapitulo)).findFirst().orElse(null);
            if (c!=null){
                Actor a = new Actor(nombreActor);
                c.getActores().add(a);
                dataBase.saveSeries(series);
                return true;
            }
            return false;
        }
        return false;
    }
}
