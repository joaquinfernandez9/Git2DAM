package dao;

import domain.modelo.Raton;

import java.util.ArrayList;
import java.util.List;

public class DaoRatones {

    private static final List<Raton> ratones = new ArrayList<>();

    static {
        ratones.add(new Raton("Paco", 2));
        ratones.add(new Raton("Juan", 3));
        ratones.add(new Raton("Pepe", 4));
        ratones.add(new Raton("Luis", 5));
        ratones.add(new Raton("Manolo", 6));
        ratones.add(new Raton("Antonio", 7));
        ratones.add(new Raton("Jose", 8));
    }

    //Una peticion de ver todos los ratones posible para los roles CURIOSOS y BIOLOGO
    public List<Raton> getAll() {
        return ratones;
    }

    //Una peticion de añadir ratones que solo se podrá hacer si se tiene el role BIOLOGO.
    public Raton add(Raton raton) {
        ratones.add(raton);
        return raton;
    }

}
