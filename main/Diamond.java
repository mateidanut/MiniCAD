package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public final class Diamond implements Shape {
    private int x, y, dH, dV;
    private Color color, fillColor;

    public Diamond(final int x, final int y, final int dH, final int dV, final Color color,
                   final Color fillColor) {
        this.x = x;
        this.y = y;
        this.dH = dH;
        this.dV = dV;
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

    public int getdH() {
        return dH;
    }

    public void setdH(final int dH) {
        this.dH = dH;
    }

    public int getdV() {
        return dV;
    }

    public void setdV(final int dV) {
        this.dV = dV;
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
