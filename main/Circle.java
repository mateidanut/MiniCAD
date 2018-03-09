package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public final class Circle implements Shape {
    private int x, y, r;
    private Color color, fillColor;

    public Circle(final int x, final int y, final int r, final Color color, final Color fillColor) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = color;
        this.fillColor = fillColor;
    }

    public int getX() {
        return x;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(final int r) {
        this.r = r;
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
