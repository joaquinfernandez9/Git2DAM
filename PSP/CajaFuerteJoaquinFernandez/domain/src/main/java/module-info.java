module domain {
    requires lombok;
    exports domain;
    opens domain;
    exports domain.error;
}