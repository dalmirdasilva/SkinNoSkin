package skinnoskin;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;

/**
 *
 * @author
 */
public class Helper {

    /**
     * Make a graph with (W * H + 2) nodes
     *
     * @param bi
     * @return
     * @throws java.io.IOException
     */
    public static ImageGraph getImageGraph(BufferedImage bi) throws IOException {
        int width = bi.getWidth();
        int height = bi.getHeight();
        ImageGraph graph = new ImageGraph(width, height);
        graph.setNodes(new Node[(width * height) + 2]); // two extra nodes to represent surce and sink
        return graph;
    }

    /**
     * Save a buffered image to file
     *
     * @param bi
     * @param f
     * @throws IOException
     */
    public static void saveImg(BufferedImage bi, File f) throws IOException {
        ImageIO.write(bi, "jpg", f);
    }

    /**
     * Read an ppm image
     *
     * @param f
     * @return
     * @throws IOException
     */
    public static BufferedImage readImg(InputStream in) throws IOException {
        BufferedImage bi = ImageIO.read(in);
        return bi;
    }

    /**
     * Convert an node number to the correspondent coordinate
     *
     * @param point
     * @param width
     * @return
     */
    public static int[] integerAsCoordinate(int point, int width) {
        return new int[]{
                    point / width,
                    point % width
                };
    }

    /**
     * Convert a coordinate to the correspondent node number
     *
     * @param x
     * @param y
     * @param width
     * @return
     */
    public static int coordinateAsInteger(int x, int y, int width) {
        return (y * width + x);
    }

    /**
     * Print a buffered image to system.out
     *
     * @param bi
     * @throws IOException
     */
    public static void printPpm(BufferedImage bi) throws IOException {
        int width = bi.getWidth();
        int height = bi.getHeight();
        System.out.println("P3\n" + width + " " + height + "\n" + 255);
        Color color;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                color = new Color(bi.getRGB(x, y));
                System.out.println(color.getRed() + "\n" + color.getGreen() + "\n" + color.getBlue());
            }
        }
    }

    static BufferedImage readImg(String filePath) throws FileNotFoundException, IOException {
        return readImg(new FileInputStream(new File(filePath)));
    }
}
