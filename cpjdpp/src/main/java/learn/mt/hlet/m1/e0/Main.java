package learn.mt.hlet.m1.e0;

import learn.mt.hlet.m0.e2.Figure;
import learn.mt.hlet.m0.e2.GraphNode;
import learn.mt.hlet.m0.e2.XOField;
import learn.mt.hlet.m0.e3.GraphHelper;

public class Main {
    public static void main(String[] args) {
        GraphNode root = new GraphBuilder().build(Figure.X, new XOField(), 0);
        System.out.println(root.getNode());
        System.out.println(GraphHelper.countNodes(root));
    }
}
