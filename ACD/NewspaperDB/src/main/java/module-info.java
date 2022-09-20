module NewspaperDB {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires jakarta.jakartaee.web.api;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires jakarta.el;
    requires jakarta.annotation;
    opens jakarta.decorator;

    exports ui.main to javafx.graphics;
    exports dao;
    exports domain.modelo;
    exports ui.pantallas.principal;

    opens ui.main;
    opens ui.pantallas.principal;
    opens domain.modelo;
//    opens ui.pantallas.article;
//    opens ui.pantallas.login;
//    opens ui.pantallas.newspaper;
//    opens ui.pantallas.reader;
    opens config;
    opens fxml;


}