import java.util.LinkedList;
import java.util.Map;

public class DistanceVectorAlgorithm extends Algorithm {

    public DistanceVectorAlgorithm(LinkedList<Node> nodes, Node origin) {
        super(nodes, origin);
        for (LinkStateNode<Node, String, Integer> lsn : this.nodes)
            checkNeighbor(lsn);
    }

    private void checkNeighbor(LinkStateNode<Node, String, Integer> lsn) {
        Node self = lsn.getSelf();
        for (Map.Entry<Node, Integer> entry : self.getLinks()) {
            if (self.equals(origin))
                updateDistance(lsn, 0);
            if (entry.getKey().equals(origin))
                continue;

            int newLSNIndex = getLSNIndex(entry.getKey());
            assert newLSNIndex >= 0;

            if (updateDistance(nodes.get(newLSNIndex), lsn.getDistance() + entry.getValue()))
                checkNeighbor(nodes.get(newLSNIndex));

        }
    }

    private boolean updateDistance(LinkStateNode<Node, String, Integer> lsn, Integer newDist) {
        if (lsn.getDistance() == -1 || newDist < lsn.getDistance()) {
            lsn.setDistance(newDist);
            return true;
        }
        return false;
    }

    @Override
    public void print() {
        for (LinkStateNode<Node, String, Integer> n : nodes)
            System.out.printf("%s %s\n", n.getSelf().getName(), n.getDistance());
    }

}
