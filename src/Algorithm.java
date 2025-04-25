import java.util.LinkedList;

public abstract class Algorithm {

    private final LinkedList<Node> nodes;
    private final Node origin;

    public Algorithm(LinkedList<Node> nodes, Node origin) {
        this.nodes = new LinkedList<>(nodes);
        this.origin = origin;
    }


    public void print() {
        System.out.printf("%s - 0\n", origin.getName());
        for (Node n : nodes)
            System.out.printf("%s %s %d\n", n.getName(), n.getViaNode(), n.getDistance());
    }
}
