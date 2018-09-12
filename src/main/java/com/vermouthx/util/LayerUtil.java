package com.vermouthx.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

public class LayerUtil {

    private static BufferedImage numImage;

    static {
        try {
            numImage = ImageIO.read(ResourceUtil.getResource("score/number.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void drawNumber(Graphics g, int x, int y, int i) {
        int width = numImage.getWidth() / 10;
        int height = numImage.getHeight();
        int offset = 0;
        LinkedList<Integer> nums = new LinkedList<>();
        while (i / 10 > 0) {
            nums.addFirst(i % 10);
            i /= 10;
        }
        nums.addFirst(i);
        for (int n : nums) {
            g.drawImage(numImage, x + offset * width, y, x + offset * width + width, y + height, n * width, 0, (n + 1) * width, height, null);
            offset++;
        }
    }
}
