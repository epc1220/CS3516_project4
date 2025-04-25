import java.util.HashMap;

public class Node {

    private int distance;
    private Node ls_via;
    private final HashMap<Node, Integer> links;
    private final String name;

    public Node(String name) {
        links = new HashMap<>();
        this.name = name;
        this.distance = -1;
        this.ls_via = null;
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

    public void printLinks() {
        System.out.printf("%s: ", this.name);
        for (HashMap.Entry<Node, Integer> entry : links.entrySet())
            System.out.printf("%s-%d, ", entry.getKey().getName(), entry.getValue());
        System.out.println();
    }

    public boolean updateDistance(Node node, int newDist) {
        if (distance == -1 || newDist < distance) {
            distance = newDist;
            ls_via = node;
            return true;
        }
        return false;
    }

    public String getViaNode() {
        return ls_via.getName();
    }

    public int getDistance() {
        return distance;
    }

}
