package skinnoskin;

/**
 * Image graph
 *
 * @author
 */
public class ImageGraph extends Graph {

    private int width = 0;
    private int height = 0;

    /**
     * ImageGraph
     *
     * @param width
     * @param height
     */
    ImageGraph(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * getWidth
     *
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * setWidth
     *
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * getHeight
     *
     * @return
     */
    public int getHeight() {
        return height;
    }

    /**
     * setHeight
     *
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
