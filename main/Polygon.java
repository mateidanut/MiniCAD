package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public final class Polygon implements Shape {
    private int n;
    private Color color, fillColor;
    private int[] x, y;

    public Polygon(final int n, final int[] x, final int[] y, final Color color, final Color
                   fillColor) {
        this.n = n;
        this.color = color;
        this.fillColor = fillColor;
        this.x = x;
        this.y = y;
    }

    public int getN() {
        return n;
    }

    public void setN(final int n) {
        this.n = n;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(final Color fillColor) {
        this.fillColor = fillColor;
    }

    public int getX(final int i) {
        return x[i];
    }

    public int getY(final int i) {
        return y[i];
    }

    @Override
    public void accept(final ShapeModifier shapeModifier, final BufferedImage image) {
        shapeModifier.draw(this, image);
    }
}
