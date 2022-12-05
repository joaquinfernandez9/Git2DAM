package domain.modelo;

import lombok.Data;

import java.util.List;

@Data
public class Serie {
    private String nombre;
    private List<Capitulo> episodios;




    public Serie(String nombre, List<Capitulo> episodios) {
        this.nombre = nombre;
        this.episodios = episodios;
    }



}
