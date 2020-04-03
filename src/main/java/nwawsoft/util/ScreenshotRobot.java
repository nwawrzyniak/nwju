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
	private Rectangle area;
    private BufferedImage bi;

    /**
     * Initializes a ScreenshotRobot and sets the dimensions of Rectangle "area" and the content of the 
	 * BufferedImage "bi".
     *
     * @param screen 0 for all screens, else monitor number.
     */
    public ScreenshotRobot(final int screen) {
		// ToDo: Split. Definition of area stays in constructor. Creation of BufferedImage goes into its own method.
        GraphicsDevice[] monitors = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        area = new Rectangle(0, 0, 0, 0);
        bi = null;
        if (screen == 0) { // case: all monitors
            for (GraphicsDevice monitor : monitors) {
                area = area.union(monitor.getDefaultConfiguration().getBounds());
            }
            try {
                bi = new Robot().createScreenCapture(area);
            } catch (AWTException e) {
                e.printStackTrace();
            }
        }
        else if (screen < 0) { // case: error: negative screen number
            System.err.println("Negative monitor index specified. Doing nothing.");
        }
        else { // case monitor specified
            try {
                if (screen - 1 < monitors.length) {
                    area = monitors[screen - 1].getDefaultConfiguration().getBounds();
                    bi = new Robot().createScreenCapture(area);
                } else {
                    System.err.println("Not enough monitors found. Selected monitor " + screen + ". Found " + 
						monitors.length + " monitors. Doing nothing.");
                }
            } catch (AWTException e) {
                e.printStackTrace();
            }
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

    public static void main(String[] args) {
        ScreenshotRobot sr = new ScreenshotRobot(2);
        sr.capture();
    }
}
