package main;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

public final class ShapeFiller implements ShapeModifier {

    public static final int THREE = 3;
    private ShapeFiller() {
    }

    private static ShapeFiller shapeFiller = new ShapeFiller();

    public static ShapeFiller getShapeFiller() {
        return shapeFiller;
    }

    private SafePixelSetter safePixelSetter = SafePixelSetter.getSafePixelSetter();

    @Override
    public void draw(final Line line, final BufferedImage image) {
    }

    @Override
    public void draw(final Square square, final BufferedImage image) {
        int i, j, x, y, l;
        x = square.getX();
        y = square.getY();
        l = square.getL();
        for (i = x + 1; i < x + l - 1; ++i) {
            for (j = y + 1; j < y + l - 1; ++j) {
                safePixelSetter.set(i, j, square.getFillColor(), image);
            }
        }
    }

    @Override
    public void draw(final Rectangle rectangle, final BufferedImage image) {
        int i, j, x, y, l, h;
        x = rectangle.getX();
        y = rectangle.getY();
        l = rectangle.getL();
        h = rectangle.getH();
        for (i = x + 1; i < x + l - 1; ++i) {
            for (j = y + 1; j < y + h - 1; ++j) {
                safePixelSetter.set(i, j, rectangle.getFillColor(), image);
            }
        }
    }

    @Override
    public void draw(final Circle circle, final BufferedImage image) {
        floodFill(circle.getX(), circle.getY(), circle.getColor(), circle.getFillColor(), image);
    }

    @Override
    public void draw(final Triangle triangle, final BufferedImage image) {
        int x, y;
        x = (triangle.getX1() + triangle.getX2() + triangle.getX3()) / THREE;
        y = (triangle.getY1() + triangle.getY2() + triangle.getY3()) / THREE;
        floodFill(x, y, triangle.getColor(), triangle.getFillColor(), image);
    }

    @Override
    public void draw(final Diamond diamond, final BufferedImage image) {
        floodFill(diamond.getX(), diamond.getY(), diamond.getColor(), diamond.getFillColor(),
                image);
    }

    @Override
    public void draw(final Polygon polygon, final BufferedImage image) {
        int x = 0, y = 0;
        for (int i = 0; i < polygon.getN(); ++i) {
            x += polygon.getX(i);
            y += polygon.getY(i);
        }
        x /= polygon.getN();
        y /= polygon.getN();
        floodFill(x, y, polygon.getColor(), polygon.getFillColor(), image);
    }

    private void floodFill(final int x, final int y, final Color color, final Color fillColor,
                           final BufferedImage image) {
        Queue<Point> queue = new LinkedList<Point>();
        Point p = new Point(x, y);
        queue.add(p);

        int w = image.getWidth();
        int h = image.getHeight();

        while (!queue.isEmpty()) {
            p = queue.remove();
            if (p.x >= 0 && p.y >= 0 && p.x < w && p.y < h && image.getRGB(p.x, p.y) != color
                    .getRGB() && image.getRGB(p.x, p.y) != fillColor.getRGB()) {
                image.setRGB(p.x, p.y, fillColor.getRGB());
                queue.add(new Point(p.x, p.y - 1));
                queue.add(new Point(p.x + 1, p.y));
                queue.add(new Point(p.x, p.y + 1));
                queue.add(new Point(p.x - 1, p.y));
            }
        }
    }

    private class Point {

        private int x, y;

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        Point(final int x, final int y) {
            this.x = x;
            this.y = y;
        }
    }

}

