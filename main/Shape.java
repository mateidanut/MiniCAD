package main;
import java.awt.image.BufferedImage;

public interface Shape {
    void accept(ShapeModifier shapeModifier, BufferedImage image);
}
