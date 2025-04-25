import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class NodeManager {

    private final LinkedList<Node> nodes;

    public NodeManager() {
        this.nodes = new LinkedList<>();
    }


    public boolean parseInstruction(@NotNull String instruction) {

        try {
            if (instruction.contains("ls") || instruction.contains("dv"))
                return parseAlgorithmInstruction(instruction);

            String[] components = instruction.split(" ");
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
            return false;
        }
        return true;
    }

    private void executeInstruction(Node n1, Node n2, int value) {
        // add link if value is a positive integer, delete link if value is -1, otherwise do nothing
        if (value >= 0) {
            n1.addLink(n2, value);
            n2.addLink(n1, value);
        }
        else if (value == -1) {
            n1.deleteLink(n2);
            n2.deleteLink(n1);
        }
        else
            System.err.printf("No action occurred. [value = %d].\n", value);
    }

    private boolean parseAlgorithmInstruction(@NotNull String instruction) {
        String[] components = instruction.split(" ");
        try {
            if (components.length != 2)
                throw new InvalidInputException("Invalid number of components.");
            if (!nodeExists(components[1]))
                throw new InvalidInputException("Node does not exist.");

            Algorithm algorithm = null;
            if (components[0].equalsIgnoreCase("ls"))
                algorithm = new LinkStateAlgorithm(nodes, getNode(components[1]));
            else if (components[0].equalsIgnoreCase("dv"))
                algorithm = new DistanceVectorAlgorithm(nodes, getNode(components[1]));
            else
                throw new InvalidInputException("Instruction not recognized");

            algorithm.print();

        } catch (InvalidInputException e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
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

    public void manualInput() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter node instructions in the format [X Y val].");
        System.out.println("When finished adding nodes, type an algorithm instruction [ls X, dv X]");
        String input = sc.nextLine();
        while (!input.equals("X")) {
            if (!parseInstruction(input))
                System.out.println("Re-enter instruction: ");
            else
                System.out.println("Enter an instruction: ");
            input = sc.nextLine();
        }
    }

    public void readFromFile(String filename) {
        try (BufferedReader fr = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = fr.readLine()) != null)
                parseInstruction(line);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    public void printNodes() {
        for (Node n : nodes)
            n.printLinks();
    }

    public static void main(String[] args) {

        NodeManager network = new NodeManager();

        switch (args.length) {
            case 0 -> network.manualInput();
            case 1 -> network.readFromFile(args[0]);
            default -> System.err.println("Invalid command line args.");
        }

        network.printNodes();
    }

}
