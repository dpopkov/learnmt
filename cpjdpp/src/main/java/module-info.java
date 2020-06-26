module cpjdpp {
//    requires javafx.fxml;
    requires javafx.controls;
    requires slf4j.api;
    requires org.apache.logging.log4j;
    opens learn.mt.cpjdpp.hello to javafx.controls;
    exports learn.mt.cpjdpp.hello;
    exports learn.mt.cpjdpp.particles;
}
