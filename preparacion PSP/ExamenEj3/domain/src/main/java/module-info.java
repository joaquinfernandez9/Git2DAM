module domain {
    requires lombok;
    requires java.sql;
    opens domain.modelo;
    exports domain.modelo;
    exports domain.modelo.error;
}