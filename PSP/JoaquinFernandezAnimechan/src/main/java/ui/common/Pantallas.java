package ui.common;

public enum Pantallas {

    MENU("/fxml/menu.fxml"),
    MAZOS("/fxml/buscarCarta.fxml"),
    CARTAS("/fxml/cartas.fxml"),
    FILTRO("/fxml/filter.fxml");
    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}
}
