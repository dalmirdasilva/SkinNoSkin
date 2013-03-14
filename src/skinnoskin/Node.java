package skinnoskin;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class Node implements Cloneable {

    private List<Edge> edges = new ArrayList<Edge>();

    /**
     * addEdge
     *
     * @param edge
     */
    public void addEdge(Edge edge) {
        getEdges().add(edge);
    }

    /**
     * getEdge
     *
     * @param index
     * @return
     */
    public Edge getEdge(int index) {
        return getEdges().get(index);
    }

    /**
     * edgeSize
     *
     * @return
     */
    public int edgeSize() {
        return getEdges().size();
    }

    /**
     * getEdges
     *
     * @return
     */
    public List<Edge> getEdges() {
        return edges;
    }

    /**
     * setEdges
     *
     * @param edges
     */
    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    /**
     * clone
     *
     * @return
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Node clone() throws CloneNotSupportedException {
        Node clone = (Node) super.clone();
        clone.setEdges(new ArrayList<Edge>());
        for (int i = 0; i < getEdges().size(); i++) {
            clone.getEdges().add(getEdges().get(i).clone());
        }
        return clone;
    }
}
