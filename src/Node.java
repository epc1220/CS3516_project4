import java.util.HashMap;

public class Node {

    private final HashMap<Node, Integer> adjacentNodes;
    private final String name;

    public Node(String name) {
        adjacentNodes = new HashMap<>();
        this.name = name;
    }

    // checks the status of any connections between this.node and other.node and updates the link accordingly
    public void updateLink(Node node, String value) {
        try {
            int val = Integer.parseInt(value);

            // if a link exists, update existing value to the new value, otherwise add a new link
            if (adjacentNodes.replace(node,val) == null) {
                addLink(node, val);
            }

        } catch (NumberFormatException e) { // if value was not a number
            // check for deletion key
            if (value.equals("-")) {
                // delete the link in this node and in the linked node if it exists
                if (linkExists(node)) {
                    deleteLink(node);
                    node.deleteLink(this);
                }
                else {
                    System.err.printf("Edge %s-%s does not exist.\n", this.getName(), node.getName());
                }
            }
            else {
                System.err.printf("%s: Invalid value input, %s.\n", e, value);
            }
        }
    }

    // remove link on this node
    private void deleteLink(Node node) {
        adjacentNodes.remove(node);
    }

    // adds a link between this.node and a new node
    private void addLink(Node node, int value) {
        adjacentNodes.put(node,value);
    }

    // checks if a link already exists
    private boolean linkExists(Node node) {
        return adjacentNodes.get(node) != null;
    }

    // returns name of node
    public String getName() {
        return this.name;
    }

}
