module servidor {
    requires domain;
    requires jakarta.jakartaee.web.api;
    requires spring.jdbc;
    requires lombok;
    requires java.logging;
    requires spring.tx;
    requires java.sql;
    requires com.zaxxer.hikari;
    requires io.vavr;
    requires org.apache.logging.log4j;
    requires modelmapper;
    requires jakarta.mail;
    requires jjwt.api;
}