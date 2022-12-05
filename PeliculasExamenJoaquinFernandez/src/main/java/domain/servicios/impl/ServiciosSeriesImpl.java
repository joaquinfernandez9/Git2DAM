package domain.servicios.impl;

import dao.DaoSeries;
import domain.modelo.Serie;
import domain.servicios.ServiciosSeries;
import jakarta.inject.Inject;

import java.util.List;

public class ServiciosSeriesImpl implements ServiciosSeries {

    private final DaoSeries daoSeries;

    @Inject
    public ServiciosSeriesImpl(DaoSeries daoSeries) {
        this.daoSeries = daoSeries;
    }

    @Override
    public boolean añadirSerie(Serie serie) {
        return daoSeries.añadirSerie(serie);
    }

    @Override
    public List<Serie> verSeries() {
        return daoSeries.verSeries();
    }

    @Override
    public boolean añadirActor(String nombreSerie, String nombreCapitulo, String nombreActor) {
        return daoSeries.añadirActor(nombreSerie, nombreCapitulo, nombreActor);
    }

}

