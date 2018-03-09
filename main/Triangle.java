package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public final class Triangle implements Shape {
    private int x1, x2, x3, y1, y2, y3;
    private Color color, fillColor;

    public Triangle(final int x1, final int x2, final int x3, final int y1, final int y2, final int
            y3, final Color color, final Color fillColor) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.color = color;
        this.fillColor = fillColor;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(final int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(final int x2) {
        this.x2 = x2;
    }

    public int getX3() {
        return x3;
    }

    public void setX3(final int x3) {
        this.x3 = x3;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(final int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(final int y2) {
        this.y2 = y2;
    }

    public int getY3() {
        return y3;
    }

    public void setY3(final int y3) {
        this.y3 = y3;
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

    @Override
    public void accept(final ShapeModifier shapeModifier, final BufferedImage image) {
        shapeModifier.draw(this, image);
    }
}
