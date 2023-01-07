module domain {
    exports model;
    exports model.querys;
    requires lombok;
    requires spring.jdbc;
    requires java.sql;

    opens model;
    opens model.querys;

}