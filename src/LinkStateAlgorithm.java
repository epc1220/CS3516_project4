import java.util.LinkedList;
import java.util.Map;

public class LinkStateAlgorithm extends Algorithm {


    public LinkStateAlgorithm(LinkedList<Node> nodes, Node origin) {
        super(nodes, origin);
        for (LinkStateNode<Node, String, Integer> lsn : this.nodes)
            checkNeighbor(lsn);
    }

    private void checkNeighbor(LinkStateNode<Node, String, Integer> lsn) {
        Node self = lsn.getSelf();
        for (Map.Entry<Node, Integer> entry : self.getLinks()) {
            if (self.equals(origin))
                updateDistance(lsn, null, 0);
            if (entry.getKey().equals(origin))
                continue;

            int newLSNIndex = getLSNIndex(entry.getKey());
            assert newLSNIndex >= 0;

            if (updateDistance(nodes.get(newLSNIndex), self, lsn.getDistance() + entry.getValue()))
                checkNeighbor(nodes.get(newLSNIndex));

        }
    }

    private boolean updateDistance(LinkStateNode<Node, String, Integer> lsn, Node via, Integer newDist) {
        if (lsn.getDistance() == -1 || newDist < lsn.getDistance()) {
            lsn.setDistance(newDist);
            if (via != null)
                lsn.setVia(via.getName());
            return true;
        }
        return false;
    }

    private int getLSNIndex(Node node) {
        for (LinkStateNode<Node, String, Integer> n : nodes)
            if (n.getSelf().equals(node))
                return nodes.indexOf(n);
        return -1;
    }

}
