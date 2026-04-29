import java.awt.*;
import java.awt.image.BufferedImage;

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
     * Converts the given image to grayscale by averaging
     * values of each pixel and setting them to the same value.
     * @param img the original image.
     * @return a new grayscale version of the image.
     */
    static BufferedImage grayscale(BufferedImage img) {

        int width  = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {

                int rgb = img.getRGB(x, y);
                Color color = new Color (rgb);
                int r = color.getRed();
                int g = color.getGreen();
                int b = color.getBlue();

                int gray  = (r + g + b)/3;
                Color grayColor = new Color(gray, gray, gray);

                newImg.setRGB(x, y, grayColor.getRGB());
            }
        }

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
     * Repeats the given image n times.
     * @param img the input image.
     * @param n   how many times to repeat it.
     * @param dir whether to repeat horizontally or vertically.
     * @return the repeated image.
     */
    static BufferedImage repeat(BufferedImage img, int n, RepeatMenuItem.RepeatDirection dir) {

        int width = img.getWidth();
        int height = img.getHeight();

        BufferedImage newImg = null;

        if (dir == RepeatMenuItem.RepeatDirection.HORIZONTAL) {
            newImg = new BufferedImage(width * n, height, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < n; i++) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        int rgb = img.getRGB(x, y);
                        newImg.setRGB(x + i * width, y, rgb);
                    }
                }
            }

        } else {
            newImg = new BufferedImage(width, height * n, BufferedImage.TYPE_INT_RGB);

            for (int i = 0; i < n; i++) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        int rgb = img.getRGB(x, y);
                        newImg.setRGB(x, y + i * height, rgb);
                    }
                }
            }
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
