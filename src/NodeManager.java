import java.util.LinkedList;

public class NodeManager {

    private LinkedList<Node> nodes;

    public NodeManager() {
        this.nodes = new LinkedList<>();
    }


    public void parseInstruction(String instruction) {
        String[] components = instruction.split(" ");

        try {
            // make sure instruction is appropriate length
            if (components.length != 3)
                throw new InvalidInstructionException(instruction);

            // check that first two components are letters, and parse the last component
            for (int i = 0; i < components.length; i++) {
                if (i < 2) { // [first two components]
                    if (components[i].matches("[a-zA-Z]+"))
                        components[i] = components[i].toUpperCase();
                    else
                        throw new InvalidInstructionException(instruction);
                    addNode(components[i]);
                }
                else { // [last component]
                    if (!components[i].matches("[0-9]+") && !components[i].equals("-"))
                        throw new InvalidInstructionException(instruction);
                }
            }

            executeInstruction( getNode(components[0]),
                                getNode(components[1]),
                                components[2].equals("-") ? -1 : Integer.parseInt(components[2]) );
        }
        catch (InvalidInstructionException e) {
            System.err.println(e.getMessage());
        }
    }

    private void executeInstruction(Node n1, Node n2, int value) {
        // add link if value is a positive integer, delete link if value is -1, otherwise do nothing
        if (value >= 0)
            n1.addLink(n2, value);
        else if (value == -1) {
            n1.deleteLink(n2);
            n2.deleteLink(n1);
        }
        else
            System.err.printf("No action occurred. [value = %d].\n", value);
    }

    private void addNode(String name) {
        // if node doesn't exist, add it
        if (!nodeExists(name))
            nodes.add(new Node(name));
    }

    private Node getNode(String name) {
        for (Node n : nodes)
            if (n.getName().equals(name))
                return n;
        return null;
    }

    private boolean nodeExists(String name) {
        return (getNode(name) != null);
    }


    // algorithms
    public void runLinkState() {

    }
    public void runDistanceVector() {

    }


    public static void main(String[] args) {

        NodeManager network = new NodeManager();


    }

}
