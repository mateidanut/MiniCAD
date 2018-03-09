package main;

import java.awt.Color;
import java.awt.image.BufferedImage;

public final class SafePixelSetter {

    private SafePixelSetter() {
    }

    private static SafePixelSetter safePixelSetter = new SafePixelSetter();

    public static SafePixelSetter getSafePixelSetter() {
        return safePixelSetter;
    }
    // functie care coloreaza un pixel si verifica inainte daca se afla intr-o pozitie valida
    public void set(final int x, final int y, final Color color, final BufferedImage image) {
        if (x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight()) {
            image.setRGB(x, y, color.getRGB());
        }
    }
}
