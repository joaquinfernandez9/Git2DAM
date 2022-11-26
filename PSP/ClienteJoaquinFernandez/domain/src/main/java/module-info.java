module domain {
    exports model;
    exports model.querys;
    requires lombok;

    opens model;
    opens model.querys;

}