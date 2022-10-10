module JoaquinFernandezAnimechan {

    requires retrofit2;
    requires lombok;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires retrofit2.converter.moshi;
    requires moshi;
    requires okhttp3;
    requires okio;
    requires io.vavr;
    requires annotations;

    exports dao;
    exports ui.pantallas.sets;
    exports domain.servicios;
    exports domain.modelo;
    exports dao.retrofit;
    opens ui.pantallas.sets;
    exports ui.main;
    exports ui.common;
    exports ui.pantallas.menu;
    exports ui.pantallas.unicaCarta;
    exports ui.pantallas.cartas;
    exports ui.pantallas.principal;
    exports dao.retrofit.cards;
    exports ui.pantallas.filtro;

    opens dao.retrofit.cards;
    opens ui.pantallas.filtro;
    opens ui.main;
    opens ui.pantallas.menu;
    opens ui.pantallas.principal;
    opens ui.pantallas.cartas;
    opens ui.pantallas.unicaCarta;
    opens ui.common;
    exports dao.retrofit.llamada;
    exports dao.impl;
    exports domain.servicios.impl;
    exports common;

}