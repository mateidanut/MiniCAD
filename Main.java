import main.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

public final class Main {

    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int OFFSET = 24;
    public static final int BASE = 16;

    private Main() {
    }

    public static void main(final String[] args) {

        File outputFile = new File("drawing.png");
        File inputFile = new File(args[0]);

        Scanner scanner = null;
        try {
            scanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int n, i, j;
        String line;
        String[] parameters;

        n = scanner.nextInt();
        scanner.nextLine();

        line = scanner.nextLine();
        parameters = line.split(" ");

        int x, y, rgb, alfa, canvasColor;
        y = Integer.parseInt(parameters[1]);
        x = Integer.parseInt(parameters[2]);
        rgb = Integer.parseInt(parameters[THREE].substring(1), BASE);
        alfa = Integer.parseInt(parameters[FOUR]);
        canvasColor = rgb + (alfa << OFFSET);

        BufferedImage image = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        // pana aici am creat canvasul si urmeaza sa ii setez culoarea
        for (i = 0; i < x; ++i) {
            for (j = 0; j < y; ++j) {
                image.setRGB(i, j, canvasColor);
            }
        }

        Shape shape;
        ShapeFactory shapeFactory = ShapeFactory.getFactory();
        ShapeModifier shapeOutliner = ShapeOutliner.getShapeOutliner();  // algoritmul de desenare
        ShapeModifier shapeFiller = ShapeFiller.getShapeFiller();  // algoritmul de umplere

        for (i = 2; i <= n; ++i) {  // citim formele si le desenam
            line = scanner.nextLine();
            System.out.println(line);
            parameters = line.split(" ");
            shape = shapeFactory.getShape(parameters);
            shape.accept(shapeOutliner, image);
            shape.accept(shapeFiller, image);
        }

        try {
            ImageIO.write(image, "PNG", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
