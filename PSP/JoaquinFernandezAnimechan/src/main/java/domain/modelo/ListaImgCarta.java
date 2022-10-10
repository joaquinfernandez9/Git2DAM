package domain.modelo;

import lombok.Data;

@Data
public class ListaImgCarta {
    private String urlImg;
    public ListaImgCarta(String urlImg) {
        this.urlImg = urlImg;
    }
}
