package learn.mt.hlet.m0.e3;

import learn.mt.hlet.m0.e2.Figure;
import learn.mt.hlet.m0.e2.GraphNode;
import learn.mt.hlet.m0.e2.XOField;

import java.util.HashSet;
import java.util.Set;

public class GraphBuilder {
    public GraphNode build(Figure currentFigure, XOField currentField) {
        Figure nextFigure = (currentFigure == Figure.X) ? Figure.O : Figure.X;
        Set<GraphNode> children = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (currentField.getFigure(i, j) == null) {
                    XOField newField = new XOField(currentField);
                    newField.setFigure(i, j, nextFigure);
                    children.add(build(nextFigure, newField));
                }
            }
        }
        return new GraphNode(currentField, children);
    }
}
