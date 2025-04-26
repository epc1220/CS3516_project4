import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Node {

    public final HashMap<Node, Integer> links;
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder(String.format("%s: ", this.name));
        for (HashMap.Entry<Node, Integer> entry : links.entrySet())
            s.append(String.format("%s-%d, ", entry.getKey().getName(), entry.getValue()));
        return s.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (this == o)
            return true;
        if (!(o instanceof Node))
            return false;
        Node other = (Node) o;

        return name.equals(other.getName());
    }

    public Set<Map.Entry<Node, Integer>> getLinks() {
        return links.entrySet();
    }

}
