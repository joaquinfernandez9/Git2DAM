module javafx {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires weld.api;
    requires MaterialFX;

    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;

    requires jakarta.cdi;
    requires jakarta.inject;

    exports ui.main;
    exports ui.pantallas.common;
    exports ui.pantallas.login;
//    exports ui.pantallas.newspaper;
    exports ui.pantallas.principal;
//    exports ui.pantallas.reader;
    exports dao;
    exports domain.services;
    exports domain.modelo;
    exports ui.pantallas.welcome;

    opens ui.main;
    opens ui.pantallas.principal;
    opens domain.modelo;
    opens ui.pantallas.login;
    opens fxml;



    requires jakarta.el;
    requires jakarta.annotation;
}