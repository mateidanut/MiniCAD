package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public final class Bresenham {
    private static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int SIX = 6;
    public static final int TEN = 10;

    private Bresenham() {
    }

    private static Bresenham bresenham = new Bresenham();

    public static Bresenham getBresenham() {
        return bresenham;
    }

    private SafePixelSetter safePixelSetter = SafePixelSetter.getSafePixelSetter();

    public void drawLine(final int x1, final int y1, final int x2, final int y2, final Color
            color, final BufferedImage image) {
        int x, y, deltaX, deltaY, error, sX, sY;
        boolean interchanged;

        x = x1;
        y = y1;
        deltaX = Math.abs(x2 - x1);
        deltaY = Math.abs(y2 - y1);

        if (x2 - x1 < 0) {
            sX = -1;
        } else {
            sX = 1;
        }
        if (y2 - y1 < 0) {
            sY = -1;
        } else {
            sY = 1;
        }

        if (deltaY > deltaX) {
            int aux = deltaX;
            deltaX = deltaY;
            deltaY = aux;
            interchanged = true;
        } else {
            interchanged = false;
        }

        error = 2 * deltaY - deltaX;

        for (int i = 0; i <= deltaX; ++i) {
            safePixelSetter.set(x, y, color, image);
            while (error > 0) {
                if (interchanged) {
                    x += sX;
                } else {
                    y += sY;
                }
                error -= 2 * deltaX;
            }
            if (interchanged) {
                y += sY;
            } else {
                x += sX;
            }
            error += 2 * deltaY;
        }
    }

    public void drawCircle(final int x, final int y, final int r, final Color color,
                           final BufferedImage image) {
        int p, q, d;
        p = 0;
        q = r;
        d = THREE - 2 * r;
        while (p <= q) {
            drawPart(x, y, p, q, color, image);
            ++p;
            if (d < 0) {
                d += FOUR * p + SIX;
            } else {
                --q;
                d += FOUR * (p - q) + TEN;
            }
            drawPart(x, y, p, q, color, image);
        }
    }

    private void drawPart(final int x, final int y, final int p, final int q, final Color color,
                          final BufferedImage image) {
        safePixelSetter.set(x + p, y + q, color, image);
        safePixelSetter.set(x - p, y + q, color, image);
        safePixelSetter.set(x + p, y - q, color, image);
        safePixelSetter.set(x - p, y - q, color, image);
        safePixelSetter.set(x + q, y + p, color, image);
        safePixelSetter.set(x - q, y + p, color, image);
        safePixelSetter.set(x + q, y - p, color, image);
        safePixelSetter.set(x - q, y - p, color, image);
    }

}
