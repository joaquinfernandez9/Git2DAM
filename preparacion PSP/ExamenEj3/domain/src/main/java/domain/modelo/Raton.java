package domain.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Raton {
    private String nombre;
    private Integer edad;

    public Raton(String nombre, Integer edad) {
        this.nombre = nombre;
        this.edad = edad;
    }
}
