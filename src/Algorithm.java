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


    public int getLSNIndex(Node node) {
        for (LinkStateNode<Node, String, Integer> n : nodes)
            if (n.getSelf().equals(node))
                return nodes.indexOf(n);
        return -1;
    }

    public void print() {
        for (LinkStateNode<Node, String, Integer> n : nodes)
            System.out.println(n);
    }
}
