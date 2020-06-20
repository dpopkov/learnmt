module cpjdpp {
//    requires javafx.fxml;
    requires javafx.controls;
    opens learn.mt.cpjdpp.hello to javafx.controls;
    exports learn.mt.cpjdpp.hello;
    exports learn.mt.cpjdpp.particles;
}
