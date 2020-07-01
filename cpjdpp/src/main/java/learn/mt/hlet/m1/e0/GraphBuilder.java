package learn.mt.hlet.m1.e0;

import learn.mt.hlet.m0.e2.Figure;
import learn.mt.hlet.m0.e2.GraphNode;
import learn.mt.hlet.m0.e2.XOField;

import java.util.*;

public class GraphBuilder {
    @SuppressWarnings("AnonymousHasLambdaAlternative")
    public GraphNode build(Figure currentFigure, XOField currentField, int deepLevel) {
        if (deepLevel > 3) {
            return new GraphNode(currentField, Collections.emptySet());
        }
        List<Thread> threads = new ArrayList<>();
        Figure nextFigure = (currentFigure == Figure.X) ? Figure.O : Figure.X;
        Set<GraphNode> children = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentField.getFigure(i, j) == null) {
                    XOField newField = new XOField(currentField);
                    newField.setFigure(i, j, nextFigure);
                    Thread threadThatAddsChildren = new Thread() {
                        @Override
                        public void run() {
                            children.add(build(nextFigure, newField, deepLevel + 1));
                        }
                    };
                    threadThatAddsChildren.start();
                    threads.add(threadThatAddsChildren);
                }
            }
        }
        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return new GraphNode(currentField, children);
    }
}
