package skinnoskin;

/**
 *
 * @author
 */
public class Edge implements Cloneable {

    private int destination = 0;
    private int weight = 0;

    /**
     * Edge
     *
     * @param destination
     * @param weight
     */
    Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    /**
     * getDestination
     *
     * @return
     */
    public int getDestination() {
        return destination;
    }

    /**
     * setDestination
     *
     * @param destination
     */
    public void setDestination(int destination) {
        this.destination = destination;
    }

    /**
     * getWeight
     *
     * @return
     */
    public int getWeight() {
        return weight;
    }

    /**
     * setWeight
     *
     * @param weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * plusWeight
     *
     * @param value
     */
    public void plusWeight(int value) {
        weight += value;
    }

    /**
     * clone
     *
     * @return
     * @throws java.lang.CloneNotSupportedException
     */
    @Override
    public Edge clone() throws CloneNotSupportedException {
        return (Edge) super.clone();
    }
}