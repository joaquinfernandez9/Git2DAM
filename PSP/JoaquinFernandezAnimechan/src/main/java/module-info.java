module JoaquinFernandezAnimechan {

    requires retrofit2;
    requires lombok;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.google.gson;
    requires retrofit2.converter.moshi;
    requires moshi;
    requires okhttp3;
    requires okio;

    exports dao;
    exports domain.servicios;
    exports ui;
    exports ui.main;
    exports ui.common;
    exports ui.pantallas.menu;
    exports ui.pantallas.mazos;
    exports ui.pantallas.cartas;
    exports ui.pantallas.principal;
    exports domain.modelo.cards;
    exports ui.pantallas.filtro;

    opens domain.modelo.cards;
    opens ui.pantallas.filtro;
    opens ui.main;
    opens ui.pantallas.principal;
    opens ui.pantallas.cartas;
    opens ui.pantallas.mazos;

}