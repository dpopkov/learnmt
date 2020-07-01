package learn.mt.hlet.m0.e3;

import learn.mt.hlet.m0.e2.GraphNode;

import java.util.Set;

public class GraphHelper {
    public static int countNodes(GraphNode node) {
        int count = 1;
        Set<GraphNode> children = node.getChildren();
        if (children.size() != 0) {
            for (GraphNode gn : children) {
                count += countNodes(gn);
            }
        }
        return count;
    }

    public static void show(GraphNode node, int level) {
        StringBuilder sb = new StringBuilder();
        sb.append(".".repeat(Math.max(0, level * 7)));
        sb.append("  ");
        String space = sb.toString();
        String nodeStr = node.getNode().toString();
        String updatedNodeStr = nodeStr.replace("\n", "\n" + space);
        System.out.println("\n" + space + updatedNodeStr);
        for (GraphNode child : node.getChildren()) {
            show(child, level + 1);
        }
    }
}
