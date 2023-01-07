module cliente {
    requires MaterialFX;
    requires jakarta.inject;
    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.cdi;
    requires lombok;
    requires com.azure.core.serializer.json.gson;
    requires io.vavr;
    requires io.reactivex.rxjava3;
    requires retrofit2;
    requires okhttp3;
    requires okio;
    requires org.apache.logging.log4j;
    requires domain;
    requires retrofit2.adapter.rxjava3;
    requires retrofit2.converter.gson;


    exports ui.pantallas.reader.addReader;
    exports ui.pantallas.login;
    exports dao.gson;
    exports ui.pantallas.reader.updateReader;
    exports ui.pantallas.querys;
    exports ui.pantallas.reader.delete;
    exports ui.pantallas.newspaper.add;
    exports ui.pantallas.principal;
    exports ui.pantallas.welcome;
    exports ui.pantallas.newspaper.list;
    exports ui.pantallas.newspaper.update;
    exports ui.pantallas.newspaper.delete;
    exports ui.pantallas.reader.listSubscription;
    exports ui.pantallas.common;
    exports ui.main;
    exports services;
    exports services.impl;
    exports dao;
    exports dao.retrofit.llamadas;
    exports dao.impl;
    exports dao.retrofit.config;
    exports dao.retrofit;

    opens dao;
    opens dao.impl;
    opens services;
    opens services.impl;



    opens ui.pantallas.common;
    opens fxml;
    opens ui.main;
    opens ui.pantallas.login;
    opens ui.pantallas.principal;
    opens ui.pantallas.querys;
    opens ui.pantallas.reader.addReader;
    opens ui.pantallas.reader.delete;
    opens ui.pantallas.reader.updateReader;
    opens ui.pantallas.reader.listSubscription;
    opens ui.pantallas.newspaper.add;
    opens ui.pantallas.newspaper.delete;
    opens ui.pantallas.newspaper.update;
    opens ui.pantallas.newspaper.list;
    opens dao.retrofit.llamadas;
}