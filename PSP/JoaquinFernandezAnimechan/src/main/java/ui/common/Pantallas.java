package ui.common;

public enum Pantallas {

    MENU("/fxml/menu.fxml"),
    MAZOS("/fxml/buscarCarta.fxml"),
    CARTAS("/fxml/cartas.fxml"),
    SETS("/fxml/sets.fxml"),
    FILTRO("/fxml/filter.fxml");
    private final String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}
}
