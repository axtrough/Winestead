package net.raccoon.will.winestead.util;

public class Util {

    public static int getARGB(int r, int g, int b, int transparency) {
        int alpha = (int) (transparency * 2.55);

        return (alpha << 24) | (r << 16) | (g << 8) | b;
    }
}
