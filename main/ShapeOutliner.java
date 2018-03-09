package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public final class ShapeOutliner implements ShapeModifier {

    private ShapeOutliner() {
    }

    private static ShapeOutliner shapeOutliner = new ShapeOutliner();

    public static ShapeOutliner getShapeOutliner() {
        return shapeOutliner;
    }

    private Bresenham bresenham = Bresenham.getBresenham();
    private SafePixelSetter safePixelSetter = SafePixelSetter.getSafePixelSetter();

    @Override
    public void draw(final Line line, final BufferedImage image) {
        bresenham.drawLine(line.getxStart(), line.getyStart(), line.getxEnd(), line.getyEnd(),
                line.getColor(), image);
    }

    @Override
    public void draw(final Square square, final BufferedImage image) {
        int i, j, x, y, l;
        x = square.getX();
        y = square.getY();
        l = square.getL();
        for (i = x; i < x + l; ++i) {
            safePixelSetter.set(i, y, square.getColor(), image);
            safePixelSetter.set(i, y + l - 1, square.getColor(), image);
        }
        for (j = y; j < y + l; ++j) {
            safePixelSetter.set(x, j, square.getColor(), image);
            safePixelSetter.set(x + l - 1, j, square.getColor(), image);
        }
    }

    @Override
    public void draw(final Rectangle rectangle, final BufferedImage image) {
        int i, j, x, y, l, h;
        x = rectangle.getX();
        y = rectangle.getY();
        l = rectangle.getL();
        h = rectangle.getH();
        for (i = x; i < x + l; ++i) {
            safePixelSetter.set(i, y, rectangle.getColor(), image);
            safePixelSetter.set(i, y + h - 1, rectangle.getColor(), image);
        }
        for (j = y; j < y + h; ++j) {
            safePixelSetter.set(x, j, rectangle.getColor(), image);
            safePixelSetter.set(x + l - 1, j, rectangle.getColor(), image);
        }
    }

    @Override
    public void draw(final Circle circle, final BufferedImage image) {
        bresenham.drawCircle(circle.getX(), circle.getY(), circle.getR(), circle.getColor(), image);
    }

    @Override
    public void draw(final Triangle triangle, final BufferedImage image) {
        int x1, x2, x3, y1, y2, y3;
        x1 = triangle.getX1();
        x2 = triangle.getX2();
        x3 = triangle.getX3();
        y1 = triangle.getY1();
        y2 = triangle.getY2();
        y3 = triangle.getY3();
        bresenham.drawLine(x1, y1, x2, y2, triangle.getColor(), image);
        bresenham.drawLine(x2, y2, x3, y3, triangle.getColor(), image);
        bresenham.drawLine(x3, y3, x1, y1, triangle.getColor(), image);
    }

    @Override
    public void draw(final Diamond diamond, final BufferedImage image) {
        int xCenter, yCenter, dH, dV, x1, x2, x3, x4, y1, y2, y3, y4;
        xCenter = diamond.getX();
        yCenter = diamond.getY();
        dH = diamond.getdH();
        dV = diamond.getdV();
        x1 = xCenter;
        y1 = yCenter - (dV / 2);
        x2 = xCenter + (dH / 2);
        y2 = yCenter;
        x3 = xCenter;
        y3 = yCenter + (dV / 2);
        x4 = xCenter - (dH / 2);
        y4 = yCenter;
        bresenham.drawLine(x4, y4, x3, y3, diamond.getColor(), image);
        bresenham.drawLine(x3, y3, x2, y2, diamond.getColor(), image);
        bresenham.drawLine(x2, y2, x1, y1, diamond.getColor(), image);
        bresenham.drawLine(x1, y1, x4, y4, diamond.getColor(), image);
    }

    @Override
    public void draw(final Polygon polygon, final BufferedImage image) {
        int n, x1, y1, x2, y2;
        Color color;
        n = polygon.getN();
        color = polygon.getColor();
        for (int i = 0; i < n - 1; ++i) {
            x1 = polygon.getX(i);
            y1 = polygon.getY(i);
            x2 = polygon.getX(i + 1);
            y2 = polygon.getY(i + 1);
            bresenham.drawLine(x1, y1, x2, y2, color, image);
        }
        x1 = polygon.getX(n - 1);
        y1 = polygon.getY(n - 1);
        x2 = polygon.getX(0);
        y2 = polygon.getY(0);
        bresenham.drawLine(x1, y1, x2, y2, color, image);
    }
}
