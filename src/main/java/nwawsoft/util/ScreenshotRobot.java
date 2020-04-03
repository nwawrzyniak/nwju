package nwawsoft.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Screenshots all monitors or a specified monitor.
 */
public class ScreenshotRobot {
    private GraphicsDevice[] monitors;
    private Rectangle area;
    private BufferedImage bi;

    /**
     * Initializes a ScreenshotRobot.
     */
    public ScreenshotRobot() {
        init();
    }

    public static void main(String[] args) {
        ScreenshotRobot sr = new ScreenshotRobot();
        sr.configureArea(1);
        sr.createBufferedImage();
        sr.capture();
    }

    /**
     * (Re-)Initializes the ScreenshotRobot.
     */
    public void init() {
        monitors = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        area = new Rectangle(0, 0, 0, 0);
        bi = null;
    }

    /**
     * Configures the area of Rectangle "area", stores the content of the specified area into the BufferedImage "bi"
     * and saves it as file "project_root/data/screenshot.png".
     *
     * @param screen 0 for all monitors, else monitor number starting with 1 for main monitor.
     */
    public void fullCapture(final int screen) {
        configureArea(screen);
        createBufferedImage();
        capture();
    }

    /**
     * Sets the Rectangle "area" depending on which monitor is specified.
     *
     * @param screen 0 for all monitors, else monitor number starting with 1 for main monitor.
     */
    public void configureArea(final int screen) {
        if (screen == 0) { // case: all monitors
            for (GraphicsDevice monitor : monitors) {
                area = area.union(monitor.getDefaultConfiguration().getBounds());
            }
        } else if (screen < 0) { // case: error: negative screen number
            System.err.println("Negative monitor index specified. Doing nothing.");
        } else { // case: monitor specified
            if (screen - 1 < monitors.length) {
                area = monitors[screen - 1].getDefaultConfiguration().getBounds();
            } else {
                System.err.println("Not enough monitors found. Selected monitor " + screen + ". Found " +
                        monitors.length + " monitors. Doing nothing.");
            }
        }
    }

    /**
     * Creates a BufferedImage of the Rectangle "area" and stores it into "bi".
     */
    public void createBufferedImage() {
        try {
            bi = new Robot().createScreenCapture(area);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves the current content of bi as file "project_root/data/screenshot.png".
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void capture() {
        try {
            new File("data/").mkdir();
            if (bi != null) {
                ImageIO.write(bi, "png", new File("data/screenshot.png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
