module servidor {
    requires domain;
    requires seguridad;
    requires lombok;
    requires java.sql;
    requires mysql.connector.java;
    requires com.zaxxer.hikari;
    requires spring.core;
    requires spring.beans;
    requires jakarta.mail;
    requires jakarta.jakartaee.web.api;
    requires spring.jdbc;
    requires java.xml.crypto;
    requires jjwt.api;
    requires io.vavr;
}