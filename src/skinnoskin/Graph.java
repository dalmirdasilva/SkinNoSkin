package skinnoskin;

/**
 * Graph class
 *
 * @author
 */
public class Graph implements Cloneable {

    private Node[] nodes;

    /**
     * getNode
     *
     * @param index
     * @return
     */
    public Node getNode(int index) {
        return nodes[index];
    }

    /**
     * setNode
     *
     * @param index
     * @param node
     */
    public void setNode(int index, Node node) {
        this.nodes[index] = node;
    }

    /**
     * getNodes
     *
     * @return
     */
    public Node[] getNodes() {
        return nodes;
    }

    /**
     * setNodes
     *
     * @param nodes
     */
    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    /**
     * clone
     *
     * @return
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Graph clone() throws CloneNotSupportedException {
        Graph clone = (Graph) super.clone();
        clone.setNodes((Node[]) clone.getNodes().clone());
        for (int i = 0; i < clone.getNodes().length; i++) {
            clone.setNode(i, (Node) clone.getNode(i).clone());
        }
        return clone;
    }
}
