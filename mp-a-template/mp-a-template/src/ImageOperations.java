import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

class ImageOperations {

    /**
     * Makes all the red channels for the given image 0
     * @param img the given image to have its red channels put to zero
     * @return the image with all its red channels put to zero
     */
    static BufferedImage zeroRed(BufferedImage img) {
        // TODO.
        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImg = new BufferedImage(width, height, img.getType());
        for (int h = 0; h < height; h++){
            for(int w = 0; w < width; w++){
                Color rgb = new Color(img.getRGB(w, h));
                Color noRed = new Color(0, rgb.getGreen(), rgb.getBlue());
                newImg.setRGB(w, h, noRed.getRGB());
            }
        }
        return newImg;
    }

    /**
     * TODO.
     *
     * @param img TODO.
     * @return TODO.
     */
    static BufferedImage grayscale(BufferedImage img) {
        // TODO.
        BufferedImage newImg = null;
        return newImg;
    }

    /**
     * TODO.
     * @param img TODO.
     * @return TODO.
     */
    static BufferedImage invert(BufferedImage img) {
        // TODO.
        BufferedImage newImg = null;
        return newImg;
    }

    /**
     * Mirrors the given image in the given direction
     * @param img the given image to be mirrored
     * @param dir the given direction for img to be mirrored in
     * @return the mirrored version of img mirrored by dir
     */
    static BufferedImage mirror(BufferedImage img, MirrorMenuItem.MirrorDirection dir) {
        // TODO instantiate newImg with the *correct* dimensions.
        int width = img.getWidth();
        int height = img.getHeight();
        BufferedImage newImg = new BufferedImage(width, height, img.getType());
        if (dir == MirrorMenuItem.MirrorDirection.VERTICAL) {
            // TODO mirror the image vertically.
            for (int h = 0; h < height; h++){
                for(int w = 0; w < width / 2; w++){

                    newImg.setRGB(w, h, img.getRGB(w, h));
                    newImg.setRGB(width - w - 1, h, img.getRGB(w, h));
                }
            }
        } else {
            // TODO mirror the image horizontally.
            for (int h = 0; h < height / 2; h++){
                for(int w = 0; w < width; w++){
                    newImg.setRGB(w, h, img.getRGB(w, h));
                    newImg.setRGB(w, height - h - 1, img.getRGB(w, h));
                }
            }
        }
        return newImg;
    }

    /**
     * TODO.
     *
     * @param img TODO.
     * @param dir TODO.
     * @return TODO.
     */
    static BufferedImage rotate(BufferedImage img, RotateMenuItem.RotateDirection dir) {
        // TODO instantiate newImg with the *correct* dimensions.
        BufferedImage newImg = null;
        if (dir == RotateMenuItem.RotateDirection.CLOCKWISE) {
            // TODO rotate the image clockwise.
        } else {
            // TODO rotate the image counter-clockwise.
        }
        return newImg;
    }

    /**
     * TODO.
     *
     * @param img TODO.
     * @param n   TODO.
     * @param dir TODO.
     * @return TODO.
     */
    static BufferedImage repeat(BufferedImage img, int n, RepeatMenuItem.RepeatDirection dir) {
        BufferedImage newImg = null;
        // newImg must be instantiated in both branches with the correct dimensions.
        if (dir == RepeatMenuItem.RepeatDirection.HORIZONTAL) {
            // TODO repeat the image horizontally.
        } else {
            // TODO repeat the image vertically.
        }
        return newImg;
    }

    /**
     * Zooms in on the image. The zoom factor increases in multiplicatives of 10% and
     * decreases in multiplicatives of 10%.
     *
     * @param img        the original image to zoom in on. The image cannot be already zoomed in
     *                   or out because then the image will be distorted.
     * @param zoomFactor The factor to zoom in by.
     * @return the zoomed in image.
     */
    static BufferedImage zoom(BufferedImage img, double zoomFactor) {
        int newImageWidth = (int) (img.getWidth() * zoomFactor);
        int newImageHeight = (int) (img.getHeight() * zoomFactor);
        BufferedImage newImg = new BufferedImage(newImageWidth, newImageHeight, TYPE_INT_RGB);
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, newImageWidth, newImageHeight, null);
        g2d.dispose();
        return newImg;
    }
}
