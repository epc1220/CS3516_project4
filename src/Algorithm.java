import java.util.LinkedList;

public abstract class Algorithm {

    public final Node origin;
    public final LinkedList<LinkStateNode<Node, String, Integer>> nodes;

    public Algorithm(LinkedList<Node> nodes, Node origin) {
        this.origin = origin;
        this.nodes = new LinkedList<>();
        for (Node n : nodes)
            this.nodes.add(new LinkStateNode<>(n, "-", -1, n.getName()));
    }


    public void print() {
        for (LinkStateNode<Node, String, Integer> n : nodes)
            System.out.println(n);
    }
}
