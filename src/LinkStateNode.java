public class LinkStateNode<T, U, V> {
    private final T self;
    private U via;
    private V distance;
    private final String selfName;

    public LinkStateNode(T self, U via, V distance, String selfName) {
        this.self = self;
        this. via = via;
        this.distance = distance;
        this.selfName = selfName;
    }

    public T getSelf() { return self; }
    public V getDistance() { return distance; }

    public void setVia(U via) { this.via = via; }
    public void setDistance(V distance) { this.distance = distance; }


    @Override
    public String toString() {
        return String.format("%s %s %s", selfName, via, distance);
    }
}
