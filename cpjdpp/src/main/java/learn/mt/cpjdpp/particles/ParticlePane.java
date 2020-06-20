package learn.mt.cpjdpp.particles;

import javafx.scene.layout.Pane;

public class ParticlePane extends Pane {

    public ParticlePane(Particle[] particles) {
        for (Particle p : particles) {
            getChildren().add(p.getCircle());
        }
    }
}
