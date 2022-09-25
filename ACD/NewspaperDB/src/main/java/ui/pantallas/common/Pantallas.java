package ui.pantallas.common;

public enum Pantallas {

    PRINCIPAL("/fxml/principal.fxml"),
    LOGIN("/fxml/login.fxml"),
    WELCOME_SCREEN("/fxml/welcome.fxml"),
    ARTICLE_SCREEN("/fxml/article/articleList.fxml"),
    READER_SCREEN("/fxml/reader/readerList.fxml"),
    NEWS_SCREEN("/fxml/newspaper/npList.fxml");

    private final String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

}
