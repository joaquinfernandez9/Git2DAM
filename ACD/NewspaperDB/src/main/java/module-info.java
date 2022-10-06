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

    requires jakarta.cdi;
    requires jakarta.inject;

    exports ui.main;
    exports ui.pantallas.common;
    exports ui.pantallas.login;
    exports ui.pantallas.principal;
    exports dao;
    exports domain.services;
    exports domain.modelo;
    exports ui.pantallas.welcome;
    exports ui.pantallas.reader.appendArticle;
    exports ui.pantallas.reader.listSubscription;
    exports ui.pantallas.reader.delete;
    exports ui.pantallas.reader.listType;

    exports ui.pantallas.newspaper.add;
    exports ui.pantallas.newspaper.list;
    exports ui.pantallas.newspaper.delete;
    exports ui.pantallas.newspaper.update;

    exports ui.pantallas.article.add;
    exports ui.pantallas.article.list;
    exports ui.pantallas.article.delete;
    exports ui.pantallas.article.update;


    exports config;
    exports dao.localDate;

    opens ui.main;
    opens ui.pantallas.principal;
    opens domain.modelo;
    opens domain.services;
    opens ui.pantallas.login;
    opens fxml;
    opens config;
    opens ui.pantallas.newspaper.delete;
    opens ui.pantallas.article.list;
    opens ui.pantallas.article.add;
    opens ui.pantallas.reader.appendArticle;


    requires io.vavr;
    requires io.vavr.match;
    opens ui;


    opens ui.pantallas.reader.delete;
    opens ui.pantallas.reader.listSubscription;
    opens ui.pantallas.reader.listType;

    exports ui;



    requires jakarta.el;
    requires jakarta.annotation;
    requires jakarta.xml.bind;
}