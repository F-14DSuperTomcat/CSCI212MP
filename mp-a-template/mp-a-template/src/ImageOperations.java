import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

class ImageOperations {

    /**
     * TODO.
     *
     * @param img TODO.
     * @return TODO.
     */
    static BufferedImage zeroRed(BufferedImage img) {
        // TODO.
        BufferedImage newImg = null;
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
     * Edits the opened BufferedImage in the editor application by inverting the colors of
     * every pixel, returning a new BufferedImage that is a negative of the original.
     * @param img the given, opened BufferedImage
     * @return a new BufferedImage with all pixel colors inverted
     */
    static BufferedImage invert(BufferedImage img) {
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        int width = newImg.getWidth();
        int height = newImg.getHeight();
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                int pixel = img.getRGB(x, y);
                Color c = new Color(pixel);
                int r = 255 - c.getRed();
                int g = 255 - c.getGreen();
                int b = 255 - c.getBlue();
                Color invC = new Color(r, g, b);
                newImg.setRGB(x, y, invC.getRGB());
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
    static BufferedImage mirror(BufferedImage img, MirrorMenuItem.MirrorDirection dir) {
        // TODO instantiate newImg with the *correct* dimensions.
        BufferedImage newImg = null;
        if (dir == MirrorMenuItem.MirrorDirection.VERTICAL) {
            // TODO mirror the image vertically.
        } else {
            // TODO mirror the image horizontally.
        }
        return newImg;
    }

    /**
     * takes a given, opened BufferedImage and the direction that the image is to be rotated,
     * and returns a new BufferedImage that is the same as the original, but rotated in the
     * given direction
     * @param img given BufferedImage
     * @param dir direction that the image is to be rotated
     * @return new BufferedImage same as the original, but rotated the given direction
     */
    static BufferedImage rotate(BufferedImage img, RotateMenuItem.RotateDirection dir) {
        BufferedImage newImg = new BufferedImage(img.getHeight(), img.getWidth(),img.getType());
        int width = img.getWidth();
        int height = img.getHeight();
        if (dir == RotateMenuItem.RotateDirection.CLOCKWISE) {
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    int pixel = img.getRGB(x, y);
                    newImg.setRGB(height - 1 - y, x, pixel);
                }
            }
        } else {
            for(int y = 0; y < height; y++){
                for(int x = 0; x < width; x++){
                    int pixel = img.getRGB(x, y);
                    newImg.setRGB(y, width - 1 - x, pixel);
                }
            }
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
        BufferedImage newImg = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, newImageWidth, newImageHeight, null);
        g2d.dispose();
        return newImg;
    }
}
