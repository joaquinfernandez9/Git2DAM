module security {
    requires com.google.common;
    requires lombok;
    requires org.apache.logging.log4j;
    requires domain;

    exports org.example.impl;
    exports org.example;

}