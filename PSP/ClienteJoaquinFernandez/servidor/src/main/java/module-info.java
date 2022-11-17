module servidor {
    requires jakarta.jakartaee.web.api;
    requires lombok;
    requires org.yaml.snakeyaml;
    requires domain;
    requires org.apache.logging.log4j;
    requires io.vavr;
    requires spring.tx;
    requires spring.jdbc;
    requires java.logging;
    requires java.sql;
    requires com.zaxxer.hikari;
}