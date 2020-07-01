package learn.mt.hlet.m1.e1;

import learn.mt.hlet.m0.e2.Figure;
import learn.mt.hlet.m0.e2.GraphNode;
import learn.mt.hlet.m0.e2.XOField;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class GraphBuilder {
    private static final ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    public GraphNode build(Figure currentFigure, XOField currentField, int deepLevel) {
        if (deepLevel > 4) {
            return new GraphNode(currentField, Collections.emptySet());
        }
        List<Future<?>> futures = new ArrayList<>();
        Figure nextFigure = (currentFigure == Figure.X) ? Figure.O : Figure.X;
        Set<GraphNode> children = new HashSet<>();
        int newLevel = deepLevel + 1;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentField.getFigure(i, j) == null) {
                    XOField newField = new XOField(currentField);
                    newField.setFigure(i, j, nextFigure);
                    Future<?> future = EXECUTOR_SERVICE.submit(
                            () -> {
                                children.add(build(nextFigure, newField, newLevel));
                            }
                    );
                    futures.add(future);
                }
            }
        }
        for (Future<?> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        return new GraphNode(currentField, children);
    }
}
