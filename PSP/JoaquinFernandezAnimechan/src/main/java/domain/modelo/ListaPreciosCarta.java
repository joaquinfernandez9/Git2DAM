package domain.modelo;

import lombok.Data;

@Data
public class ListaPreciosCarta {
    String precioCoolStuffInc;
    String precioTCGPlayer;
    String precioAmazon;
    String precioEbay;
    String precioCardMarket;

    public ListaPreciosCarta(String precioCoolStuffInc, String precioTCGPlayer, String precioAmazon, String precioEbay, String precioCardMarket) {
        this.precioCoolStuffInc = precioCoolStuffInc;
        this.precioTCGPlayer = precioTCGPlayer;
        this.precioAmazon = precioAmazon;
        this.precioEbay = precioEbay;
        this.precioCardMarket = precioCardMarket;
    }
}
