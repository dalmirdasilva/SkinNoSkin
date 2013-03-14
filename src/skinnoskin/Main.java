package skinnoskin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author
 */
public class Main {

    public static int CONSTANT = 10000000;
    public static int PENALTY = 500;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException, IOException, Exception {

        if (args.length < 2) {
            System.out.println("Usage: program input output");
            System.exit(1);
        }

        BufferedImage buffered_image = Helper.readImg(args[0]);
        ImageGraph capacity = Helper.getImageGraph(buffered_image);

        int width = capacity.getWidth();
        int height = capacity.getHeight();

        int source = (width * height);
        int sink = source + 1;

        capacity.setNode(source, new Node());
        capacity.setNode(sink, new Node());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {

                int skin = (int) (Algorithm.skinProbability(buffered_image.getRGB(x, y)) * CONSTANT);
                int noskin = (int) (Algorithm.noSkinProbability(buffered_image.getRGB(x, y)) * CONSTANT);

                Node node = new Node();

                if (y > 0) {
                    node.addEdge(new Edge(Helper.coordinateAsInteger(x, y - 1, width), PENALTY));
                }

                if (y < capacity.getHeight() - 1) {
                    node.addEdge(new Edge(Helper.coordinateAsInteger(x, y + 1, width), PENALTY));
                }

                if (x > 0) {
                    node.addEdge(new Edge(Helper.coordinateAsInteger(x - 1, y, width), PENALTY));
                }

                if (x < capacity.getWidth() - 1) {
                    node.addEdge(new Edge(Helper.coordinateAsInteger(x + 1, y, width), PENALTY));
                }

                node.addEdge(new Edge(sink, noskin));
                node.addEdge(new Edge(source, 0));
                capacity.setNode(Helper.coordinateAsInteger(x, y, width), node);

                capacity.getNode(source).addEdge(new Edge(Helper.coordinateAsInteger(x, y, width), skin));
                capacity.getNode(sink).addEdge(new Edge(Helper.coordinateAsInteger(x, y, width), 0));
            }
        }

        Graph flow = Algorithm.edmondsKarp(capacity, source, sink);

        buffered_image = Algorithm.paintImage(buffered_image, capacity, flow, source, Color.BLACK.getRGB(), true);

        Helper.saveImg(buffered_image, new File(args[1]));
    }
}
