package learn.mt.hlet.m0.e3;

import learn.mt.hlet.m0.e2.Figure;
import learn.mt.hlet.m0.e2.GraphNode;
import learn.mt.hlet.m0.e2.XOField;

public class Main {
    public static void main(String[] args) {
        GraphNode root = new GraphBuilder().build(Figure.X, new XOField());
        System.out.println(root.getNode());
        GraphHelper.show(root, 0);
        System.out.println(GraphHelper.countNodes(root));
    }
}
