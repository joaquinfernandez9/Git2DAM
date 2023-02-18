module cliente {
    requires domain;
    requires security;

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;

    requires retrofit2;
    requires retrofit2.converter.gson;
    requires retrofit2.adapter.rxjava3;
    requires io.reactivex.rxjava3;

    requires lombok;
    requires io.vavr;
    requires org.apache.logging.log4j;

    requires java.sql;
    requires gson;
    requires okhttp3;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires jakarta.el;
}