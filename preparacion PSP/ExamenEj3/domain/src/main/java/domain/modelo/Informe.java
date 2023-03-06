package domain.modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Informe {
    private String nombre;
    private LocalDate fechaInforme;
    private String rol;
}
