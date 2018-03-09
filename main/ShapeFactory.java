package main;

import java.awt.Color;

public final class ShapeFactory {

    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int SIX = 6;
    public static final int SEVEN = 7;
    public static final int EIGHT = 8;
    public static final int NINE = 9;
    public static final int TEN = 10;
    public static final int BASE = 16;
    public static final int OFFSET = 24;

    private ShapeFactory() {
    }

    private static ShapeFactory shapeFactory = new ShapeFactory();

    public static ShapeFactory getFactory() {
        return shapeFactory;
    }

    private int getRgba(final String a, final String b) {
        int rgb, alfa;  // creeaza rgba avand valoarea rgb in hexa si valoarea alfa in decimal
        rgb = Integer.parseInt(a.substring(1), BASE);
        alfa = Integer.parseInt(b);
        return rgb + (alfa << OFFSET);
    }
    // functia getShape parseaza parametrii formei, dupa care instantiaza forma corespunzatoare
    public Shape getShape(final String[] parameters) {
        switch (parameters[0]) {
            case "LINE": {
                int xStart, yStart, xEnd, yEnd;
                Color color;
                xStart = Integer.parseInt(parameters[1]);
                yStart = Integer.parseInt(parameters[2]);
                xEnd = Integer.parseInt(parameters[THREE]);
                yEnd = Integer.parseInt(parameters[FOUR]);
                color = new Color(getRgba(parameters[FIVE], parameters[SIX]), true);

                return new Line(xStart, yStart, xEnd, yEnd, color);
            }
            case "SQUARE": {
                int x, y, l;
                Color color, fillColor;
                x = Integer.parseInt(parameters[1]);
                y = Integer.parseInt(parameters[2]);
                l = Integer.parseInt(parameters[THREE]);
                color = new Color(getRgba(parameters[FOUR], parameters[FIVE]), true);
                fillColor = new Color(getRgba(parameters[SIX], parameters[SEVEN]), true);

                return new Square(x, y, l, color, fillColor);
            }
            case "RECTANGLE": {
                int x, y, l, h;
                Color color, fillColor;
                x = Integer.parseInt(parameters[1]);
                y = Integer.parseInt(parameters[2]);
                h = Integer.parseInt(parameters[THREE]);
                l = Integer.parseInt(parameters[FOUR]);
                color = new Color(getRgba(parameters[FIVE], parameters[SIX]), true);
                fillColor = new Color(getRgba(parameters[SEVEN], parameters[EIGHT]), true);

                return new Rectangle(x, y, h, l, color, fillColor);
            }
            case "CIRCLE": {
                int x, y, r;
                Color color, fillColor;
                x = Integer.parseInt(parameters[1]);
                y = Integer.parseInt(parameters[2]);
                r = Integer.parseInt(parameters[THREE]);
                color = new Color(getRgba(parameters[FOUR], parameters[FIVE]), true);
                fillColor = new Color(getRgba(parameters[SIX], parameters[SEVEN]), true);

                return new Circle(x, y, r, color, fillColor);
            }
            case "TRIANGLE": {
                int x1, x2, x3, y1, y2, y3;
                Color color, fillColor;
                x1 = Integer.parseInt(parameters[1]);
                y1 = Integer.parseInt(parameters[2]);
                x2 = Integer.parseInt(parameters[THREE]);
                y2 = Integer.parseInt(parameters[FOUR]);
                x3 = Integer.parseInt(parameters[FIVE]);
                y3 = Integer.parseInt(parameters[SIX]);
                color = new Color(getRgba(parameters[SEVEN], parameters[EIGHT]), true);
                fillColor = new Color(getRgba(parameters[NINE], parameters[TEN]), true);

                return new Triangle(x1, x2, x3, y1, y2, y3, color, fillColor);
            }
            case "DIAMOND": {
                int x, y, dH, dV;
                Color color, fillColor;
                x = Integer.parseInt(parameters[1]);
                y = Integer.parseInt(parameters[2]);
                dH = Integer.parseInt(parameters[THREE]);
                dV = Integer.parseInt(parameters[FOUR]);
                color = new Color(getRgba(parameters[FIVE], parameters[SIX]), true);
                fillColor = new Color(getRgba(parameters[SEVEN], parameters[EIGHT]), true);

                return new Diamond(x, y, dH, dV, color, fillColor);
            }
            case "POLYGON": {
                int n;
                Color color, fillColor;
                int[] x, y;
                n = Integer.parseInt(parameters[1]);
                x = new int[n];
                y = new int[n];

                for (int i = 1; i <= n; ++i) {
                    x[i - 1] = Integer.parseInt(parameters[2 * i]);
                    y[i - 1] = Integer.parseInt(parameters[2 * i + 1]);
                }

                color = new Color(getRgba(parameters[2 * (n + 1)], parameters[2 * (n + 1) + 1]),
                        true);
                fillColor = new Color(getRgba(parameters[2 * (n + 2)], parameters[2 * (n + 2) + 1]),
                        true);

                return new Polygon(n, x, y, color, fillColor);
            }
            default:
                return null;
        }
    }
}
