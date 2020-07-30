package learn.mt.mpogr.reentrantlock;

import javafx.animation.AnimationTimer;
import javafx.animation.FillTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleSupplier;

/*
VM options для запуска JavaFX на JDK 11
--module-path path-to-javafx\javafx-sdk-11\lib
--add-modules=javafx.controls,javafx.fxml
 */
public class MainPricesUI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Prices");
        GridPane grid = createGrid();
        Map<String, Label> cryptoLabels = createCryptoPriceLabels();
        addLabelsToGrid(cryptoLabels, grid);
        double width = 300;
        double height = 250;
        StackPane root = new StackPane();
        Rectangle background = createBackgroundRectangleWithAnimation(width, height);
        root.getChildren().add(background);
        root.getChildren().add(grid);
        stage.setScene(new Scene(root, width, height));

        PricesContainer container = new PricesContainer();
        PricesUpdater updater = new PricesUpdater(container);
        Map<String, DoubleSupplier> suppliers = createSuppliers(container);
        AnimationTimer animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (container.getLockObject().tryLock()) {
                    try {
                        cryptoLabels.keySet().forEach(id -> cryptoLabels.get(id).setText(
                                String.valueOf(suppliers.get(id).getAsDouble())));
                    } finally {
                        container.getLockObject().unlock();
                    }
                }
            }
        };
        animationTimer.start();
        updater.start();
        stage.setOnCloseRequest(e -> updater.interrupt());

        stage.show();
    }

    private GridPane createGrid() {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        return grid;
    }

    private Map<String, Label> createCryptoPriceLabels() {
        Map<String, Label> map = new HashMap<>();
        createLabel("BTC", map);
        createLabel("ETH", map);
        createLabel("LTC", map);
        createLabel("BCH", map);
        createLabel("XRP", map);
        return map;
    }

    private Map<String, DoubleSupplier> createSuppliers(PricesContainer container) {
        Map<String, DoubleSupplier> map = new HashMap<>();
        map.put("BTC", container::getBitcoinPrice);
        map.put("ETH", container::getEtherPrice);
        map.put("LTC", container::getLitecoinPrice);
        map.put("BCH", container::getBitcoinCashPrice);
        map.put("XRP", container::getRipplePrice);
        return map;
    }

    private void createLabel(String id, Map<String, Label> map) {
        Label label = new Label("0");
        label.setId(id);
        map.put(id, label);
    }

    private void addLabelsToGrid(Map<String, Label> cryptoLabels, GridPane grid) {
        int row = 0;
        for (Map.Entry<String, Label> entry : cryptoLabels.entrySet()) {
            String id = entry.getKey();
            Label idLabel = new Label(id);
            idLabel.setTextFill(Color.BLUE);
            idLabel.setOnMousePressed(e -> idLabel.setTextFill(Color.RED));
            idLabel.setOnMouseReleased(e -> idLabel.setTextFill(Color.BLUE));
            grid.add(idLabel, 0, row);
            grid.add(entry.getValue(), 1, row);
            row++;
        }
    }

    private Rectangle createBackgroundRectangleWithAnimation(double width, double height) {
        Rectangle rect = new Rectangle(width, height);
        FillTransition ft = new FillTransition(Duration.millis(1000), rect, Color.LIGHTGREEN, Color.LIGHTBLUE);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setAutoReverse(true);
        ft.play();
        return rect;
    }
}
