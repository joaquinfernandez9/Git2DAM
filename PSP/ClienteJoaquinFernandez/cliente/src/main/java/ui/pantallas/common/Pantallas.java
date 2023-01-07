package ui.pantallas.common;

public enum Pantallas {

    PRINCIPAL(UiConstants.FXML_PRINCIPAL_FXML),
    WELCOME_SCREEN(UiConstants.FXML_WELCOME_FXML),
    READER_DELETE(UiConstants.FXML_READER_READER_DELETE_FXML),
    READER_LIST_SUBSCRIPTION(UiConstants.FXML_READER_READER_LIST_SUBSCRIPTION_FXML),
    READER_LIST_TYPE(UiConstants.FXML_READER_READER_LIST_TYPE_FXML),
    READER_ADD(UiConstants.FXML_READER_ADD_READER_FXML),
    READER_UPDATE(UiConstants.FXML_READER_UPDATE_READER_FXML),
    NEWS_LIST(UiConstants.FXML_NEWSPAPER_NP_LIST_FXML),
    NEWS_ADD(UiConstants.FXML_NEWSPAPER_NP_ADD_FXML),
    NEWS_DELETE(UiConstants.FXML_NEWSPAPER_NP_DELETE_FXML),
    NEWS_UPDATE(UiConstants.FXML_NEWSPAPER_NP_UPDATE_FXML),
    QUERYS(UiConstants.FXML_QUERYS_FXML),
    LOGIN("/fxml/login.fxml");

    private final String ruta;

    Pantallas(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

}
