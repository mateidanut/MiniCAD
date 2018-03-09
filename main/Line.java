package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public final class Line implements Shape {
    private int xStart, yStart, xEnd, yEnd;
    private Color color;

    public Line(final int xStart, final int yStart, final int xEnd, final int yEnd,
                final Color color) {
        this.xStart = xStart;
        this.yStart = yStart;
        this.xEnd = xEnd;
        this.yEnd = yEnd;
        this.color = color;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(final int xStart) {
        this.xStart = xStart;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(final int yStart) {
        this.yStart = yStart;
    }

    public int getxEnd() {
        return xEnd;
    }

    public void setxEnd(final int xEnd) {
        this.xEnd = xEnd;
    }

    public int getyEnd() {
        return yEnd;
    }

    public void setyEnd(final int yEnd) {
        this.yEnd = yEnd;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
    }

    @Override
    public void accept(final ShapeModifier shapeModifier, final BufferedImage image) {
        shapeModifier.draw(this, image);
    }
}
