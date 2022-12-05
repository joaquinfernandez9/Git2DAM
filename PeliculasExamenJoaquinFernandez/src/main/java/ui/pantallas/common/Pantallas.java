package ui.pantallas.common;

public enum Pantallas {

    LOGIN("/fxml/pantallaLogin.fxml"),
    ADMIN("/fxml/pantallaAdmin.fxml"),
    CLIENTE_PELIS("/fxml/pantallaClientePelis.fxml"),
    CLIENTE_SERIES("/fxml/pantallaClienteSeries.fxml");

    private String ruta;
    Pantallas(String ruta){
        this.ruta = ruta;
    }

    public String getRuta(){
        return ruta;
    }


}
