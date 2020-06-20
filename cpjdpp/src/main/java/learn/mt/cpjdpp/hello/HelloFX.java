package learn.mt.cpjdpp.hello;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class HelloFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        Circle circle = new Circle(100, 100, 50, Color.BLUE);
        circle.setOnMouseClicked(e -> {
            double x = circle.getCenterX();
            if (e.getX() < x) {
                circle.setCenterX(x - 10);
            } else {
                circle.setCenterX(x + 10);
            }
        });
        Pane pane = new Pane(circle);
        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle(HelloFX.class.getSimpleName());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
