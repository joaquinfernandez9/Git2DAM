module javafx {
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires weld.api;
    requires MaterialFX;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires io.vavr;
    requires io.vavr.match;
    requires java.sql;
    requires commons.dbcp2;
    requires spring.jdbc;
    requires spring.tx;
    requires jakarta.transaction;
    requires com.zaxxer.hikari;
    requires jakarta.el;
    requires jakarta.annotation;
    requires jakarta.xml.bind;
    requires jakarta.persistence;

    opens services.strings;
    opens dao.strings;
    opens ui.pantallas.newspaper.add;
    opens ui.pantallas.newspaper.list;
    opens ui.pantallas.newspaper.update;
    opens ui.pantallas.common;
    opens ui.pantallas.querys;
    opens ui.pantallas.reader.subscribe;
    opens ui.pantallas.welcome;
    opens ui.pantallas.article.delete;
    opens ui.pantallas.article.update;
    opens ui.pantallas.reader.addReader;
    opens ui.main;
    opens ui.pantallas.principal;
    opens model;
    opens services;
    opens ui.pantallas.login;
    opens fxml;
    opens config;
    opens ui.pantallas.newspaper.delete;
    opens ui.pantallas.article.list;
    opens ui.pantallas.article.add;
    opens ui.pantallas.reader.appendArticle;
    opens ui.pantallas.reader.delete;
    opens ui.pantallas.reader.listSubscription;
    opens ui.pantallas.reader.listType;
    opens model.querys;
    opens ui.pantallas.reader.updateReader;

    exports ui.pantallas.querys;
    exports dao.impl;
    exports ui.pantallas.reader.subscribe;
    exports services.impl;
    exports ui.main;
    exports ui.pantallas.common;
    exports ui.pantallas.login;
    exports ui.pantallas.principal;
    exports dao;
    exports services;
    exports model;
    exports ui.pantallas.welcome;
    exports ui.pantallas.reader.appendArticle;
    exports ui.pantallas.reader.listSubscription;
    exports ui.pantallas.reader.delete;
    exports ui.pantallas.reader.listType;
    exports ui.pantallas.reader.addReader;
    exports ui.pantallas.reader.updateReader;
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
    exports dao.strings;
    exports model.querys;


}