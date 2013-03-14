package skinnoskin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author
 */
public class Algorithm {

    private final static double[][] skin = new double[][]{
        {73.53, 29.94, 17.76, 765.40, 121.44, 112.80, 0.0294},
        {249.71, 233.94, 217.49, 39.94, 154.44, 396.05, 0.0331},
        {161.68, 116.25, 96.95, 291.03, 60.48, 162.85, 0.0654},
        {186.07, 136.62, 114.40, 274.95, 64.60, 198.27, 0.0756},
        {189.26, 98.37, 51.18, 633.18, 222.40, 250.69, 0.0554},
        {247.00, 152.20, 90.84, 65.23, 691.53, 609.92, 0.0314},
        {150.10, 72.66, 37.76, 408.63, 200.77, 257.57, 0.0454},
        {206.85, 171.09, 156.34, 530.08, 155.08, 572.79, 0.0469},
        {212.78, 152.82, 120.04, 160.57, 84.52, 243.90, 0.0956},
        {234.87, 175.43, 138.94, 163.80, 121.57, 279.22, 0.0763},
        {151.19, 97.74, 74.59, 425.40, 73.56, 175.11, 0.1100},
        {120.52, 77.55, 59.82, 330.45, 70.34, 151.82, 0.0676},
        {192.20, 119.62, 82.32, 152.76, 92.14, 259.15, 0.0755},
        {214.29, 136.08, 87.24, 204.90, 140.17, 270.19, 0.0500},
        {99.57, 54.33, 38.06, 448.13, 90.18, 151.29, 0.0667},
        {238.88, 203.08, 176.91, 178.38, 156.27, 404.99, 0.0749}
    };
    private final static double[][] noskin = new double[][]{
        {254.37, 254.41, 253.82, 2.77, 2.81, 5.46, 0.0637},
        {9.39, 8.09, 8.52, 46.84, 33.59, 32.48, 0.0516},
        {96.57, 96.95, 91.53, 280.69, 156.79, 436.58, 0.0864},
        {160.44, 162.49, 159.06, 355.98, 115.89, 591.24, 0.0636},
        {74.98, 63.23, 46.33, 414.84, 245.95, 361.27, 0.0747},
        {121.83, 60.88, 18.31, 2502.24, 1383.53, 237.18, 0.0365},
        {202.18, 154.88, 91.04, 957.42, 1766.94, 1582.52, 0.0349},
        {193.06, 201.93, 206.55, 562.88, 190.23, 447.28, 0.0649},
        {51.88, 57.14, 61.55, 344.11, 191.77, 433.40, 0.0656},
        {30.88, 26.84, 25.32, 222.07, 118.65, 182.41, 0.1189},
        {44.97, 85.96, 131.95, 651.32, 840.52, 963.67, 0.0362},
        {236.02, 236.27, 230.70, 225.03, 117.29, 331.95, 0.0849},
        {207.86, 191.20, 164.12, 494.04, 237.69, 533.52, 0.0368},
        {99.83, 148.11, 188.17, 955.88, 654.95, 916.70, 0.0389},
        {135.06, 131.92, 123.10, 350.35, 130.30, 388.43, 0.0943},
        {135.96, 103.89, 66.88, 806.44, 642.20, 350.36, 0.0477}
    };

    /**
     * Edmonds Karp algorithm
     *
     * @param capacity
     * @param source
     * @param sink
     * @return
     * @throws java.lang.CloneNotSupportedException
     * @throws java.lang.Exception
     */
    public static Graph edmondsKarp(Graph capacity, int source, int sink) throws CloneNotSupportedException, Exception {
        int max = 0, current = 0, next = 0;
        Graph flow = (Graph) capacity.clone();
        List<Integer> path;
        for (int i = 0; i < flow.getNodes().length; i++) {
            for (int j = 0; j < flow.getNode(i).edgeSize(); j++) {
                flow.getNode(i).getEdge(j).setWeight(0);
            }
        }
        while (true) {
            path = breadthFirstSearch(capacity, flow, source, sink);
            if (path == null) {
                break;
            }
            max = Integer.MAX_VALUE;
            for (int i = 0; i < path.size() - 1; i++) {
                current = path.get(i);
                next = path.get(i + 1);
                max = Math.min(max, (capacity.getNode(current).getEdge(getEdgeToNode(capacity.getNode(current), next)).getWeight() - flow.getNode(current).getEdge(getEdgeToNode(flow.getNode(current), next)).getWeight()));
            }
            for (int i = 0; i < path.size() - 1; i++) {
                current = path.get(i);
                next = path.get(i + 1);
                flow.getNode(current).getEdge(getEdgeToNode(flow.getNode(current), next)).plusWeight(max);
                flow.getNode(next).getEdge(getEdgeToNode(flow.getNode(next), current)).plusWeight(-(max));
            }
        }
        return flow;
    }

    /**
     * Breadth First Search algorithm
     *
     * @param capacity
     * @param flow
     * @param source
     * @param sink
     * @return
     */
    public static List<Integer> breadthFirstSearch(Graph capacity, Graph flow, int source, int sink) {
        int head = 0, to = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        List<Integer> path = new ArrayList<Integer>();
        int parent[] = new int[capacity.getNodes().length];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        parent[source] = source;
        queue.push(source);
        while (!queue.isEmpty()) {
            head = queue.poll();
            for (int edge = 0; edge < capacity.getNode(head).edgeSize(); edge++) {
                to = capacity.getNode(head).getEdge(edge).getDestination();
                if (capacity.getNode(head).getEdge(edge).getWeight() - flow.getNode(head).getEdge(edge).getWeight() > 0 && parent[to] == -1) {
                    parent[to] = head;
                    queue.push(to);
                    if (to == sink) {
                        while (true) {
                            path.add(0, to);
                            if (to == source) {
                                break;
                            }
                            to = parent[to];
                        }
                        return path;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Get edge to node
     *
     * @param node
     * @param to
     * @return
     * @throws java.lang.Exception
     */
    public static int getEdgeToNode(Node node, int to) throws Exception {
        for (int i = node.edgeSize() - 1; i >= 0; i--) {
            if (node.getEdge(i).getDestination() == to) {
                return i;
            }
        }
        throw new Exception("Oops, 'edge' not found... this should never happen!");
    }

    /**
     * Paint an image
     *
     * @param image
     * @param capacity
     * @param flow
     * @param source
     * @param rgb
     * @return
     */
    public static BufferedImage paintImage(BufferedImage image, Graph capacity, Graph flow, int source, int rgb, boolean bg) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        BufferedImage tmp = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        for (int x = 0; x < tmp.getWidth(); x++) {
            for (int y = 0; y < tmp.getHeight(); y++) {
                tmp.setRGB(x, y, rgb);
            }
        }
        int[] visited = new int[(tmp.getWidth() * tmp.getHeight()) + 2];
        int head = 0;
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }
        queue.push(source);
        while (!queue.isEmpty()) {
            head = queue.poll();
            for (int edge = 0; edge < flow.getNode(head).edgeSize(); edge++) {
                Edge capacity_edge = capacity.getNode(head).getEdge(edge);
                Edge flow_edge = flow.getNode(head).getEdge(edge);
                if (capacity_edge.getWeight() > flow_edge.getWeight() && visited[capacity_edge.getDestination()] == -1) {
                    queue.push(capacity_edge.getDestination());
                    int[] coordinate = Helper.integerAsCoordinate(capacity_edge.getDestination(), image.getWidth());
                    if (bg) {
                        tmp.setRGB(coordinate[1], coordinate[0], image.getRGB(coordinate[1], coordinate[0]));
                    } else {
                        image.setRGB(coordinate[1], coordinate[0], tmp.getRGB(coordinate[1], coordinate[0]));
                    }
                }
                visited[head] = 1;
            }
        }
        return (bg) ? tmp : image;
    }

    /**
     * Skin probability
     *
     * @param rgb
     * @return
     */
    public static double skinProbability(int rgb) {
        Color color = new Color(rgb);
        double r = 0;
        for (int i = 0; i < skin.length; i++) {
            double t;
            t = Math.pow((float) color.getRed() - skin[i][0], 2) / skin[i][3] + Math.pow((float) color.getGreen() - skin[i][1], 2) / skin[i][4] + Math.pow((float) color.getBlue() - skin[i][2], 2) / skin[i][5];
            t = skin[i][6] * Math.exp(-t / 2) / (Math.pow(2 * Math.PI, 1.5) * Math.sqrt(skin[i][3] * skin[i][4] * skin[i][5]));
            r += t;
        }
        return r;
    }

    /**
     * No skin probability
     *
     * @param rgb
     * @return
     */
    public static double noSkinProbability(int rgb) {
        Color color = new Color(rgb);
        double r = 0;
        for (int i = 0; i < noskin.length; i++) {
            double t;
            t = Math.pow((double) color.getRed() - noskin[i][0], 2) / noskin[i][3] + Math.pow((double) color.getGreen() - noskin[i][1], 2) / noskin[i][4] + Math.pow((double) color.getBlue() - noskin[i][2], 2) / noskin[i][5];
            t = noskin[i][6] * Math.exp(-t / 2) / (Math.pow(2 * Math.PI, 1.5) * Math.sqrt(noskin[i][3] * noskin[i][4] * noskin[i][5]));
            r += t;
        }
        return r;
    }
}
