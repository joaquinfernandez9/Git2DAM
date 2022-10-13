package domain.modelo;

import lombok.Data;

@Data
public class ListaSetsCarta {
    private String codigoSet;
    private String nombreSet;

    public String getCodigoSet() {
        return codigoSet;
    }

    public String getNombreSet() {
        return nombreSet;
    }

    public ListaSetsCarta(String codigoSet, String nombreSet) {
        this.codigoSet = codigoSet;
        this.nombreSet = nombreSet;
    }
}
