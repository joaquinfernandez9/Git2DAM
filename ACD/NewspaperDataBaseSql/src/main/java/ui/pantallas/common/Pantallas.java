package ui.pantallas.common;

public enum Pantallas {

    PRINCIPAL("/fxml/principal.fxml"),
    LOGIN("/fxml/login.fxml"),
    WELCOME_SCREEN("/fxml/welcome.fxml"),
    ARTICLE_LIST("/fxml/article/articleList.fxml"),
    ARTICLE_ADD("/fxml/article/articleAdd.fxml"),
    ARTICLE_DELETE("/fxml/article/articleDelete.fxml"),
    ARTICLE_UPDATE("/fxml/article/articleUpdate.fxml"),
    READER_ADD_READARTICLE("/fxml/reader/appendReadArticle.fxml"),
    READER_DELETE("/fxml/reader/readerDelete.fxml"),
    READER_LIST_SUBSCRIPTION("/fxml/reader/readerListSubscription.fxml"),
    READER_LIST_TYPE("/fxml/reader/readerListType.fxml"),
    READER_ADD("/fxml/reader/addReader.fxml"),
    READER_UPDATE("/fxml/reader/updateReader.fxml"),
    READER_SUBSCRIBE("/fxml/reader/subscribeReader.fxml"),
    NEWS_LIST("/fxml/newspaper/npList.fxml"),
    NEWS_ADD("/fxml/newspaper/npAdd.fxml"),
    NEWS_DELETE("/fxml/newspaper/npDelete.fxml"),
    NEWS_UPDATE("/fxml/newspaper/npUpdate.fxml");

    private final String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

}
