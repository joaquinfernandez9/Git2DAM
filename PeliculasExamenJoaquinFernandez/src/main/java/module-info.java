module PeliculasExamenJoaquinFernandez {
    requires jakarta.cdi;
    requires javafx.graphics;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires jakarta.inject;
    requires lombok;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires org.apache.logging.log4j;
    requires jakarta.el;
    requires jakarta.annotation;
    requires com.google.gson;
    requires weld.api;

    exports ui.main;
    exports domain.servicios.impl;
    exports dao.impl;
    exports ui.pantallas.pantallaLogin;
    exports ui.pantallas.pantallaClienteSeries;
    exports ui.pantallas.principal;
    exports ui.pantallas.pantallaClientePelis;
    exports ui.pantallas.common;
    exports ui.pantallas.pantallaAdmin;
    exports di;
    exports config;
    exports dao;
    exports domain.modelo;


    opens ui.pantallas.principal;
    opens ui.main;
    opens ui.pantallas.pantallaLogin;
    opens domain.modelo;
    opens ui.pantallas.pantallaAdmin;
}