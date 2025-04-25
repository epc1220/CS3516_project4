import java.util.HashMap;

public class Node {

    private final HashMap<Node, Integer> links;
    private final String name;

    public Node(String name) {
        links = new HashMap<>();
        this.name = name;
    }

    // adds a link between this.node and a new node
    public void addLink(Node node, int value) {
        if (links.replace(node,value) == null)
            links.put(node,value);
    }

    // remove link on this node
    public void deleteLink(Node node) {
        if (linkExists(node))
            links.remove(node);
        else
            System.err.printf("Edge %s-%s does not exist.\n", this.getName(), node.getName());
    }

    // checks if a link already exists
    private boolean linkExists(Node node) {
        return links.get(node) != null;
    }

    // returns name of node
    public String getName() {
        return this.name;
    }

}
