package com.nwawsoft.util.tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Screenshots all monitors or a specified monitor or area/region.
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

    /**
     * (Re-)Initializes the ScreenshotRobot.
     */
    public void init() {
        monitors = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
        area = new Rectangle(0, 0, 0, 0);
        bi = null;
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
     * Sets area to the specified Rectangle.
     *
     * @param area any area within the screen size.
     */
    public void setAreaManually(final Rectangle area) {
        this.area = area;
    }

    /**
     * Sets area to the specified position and size.
     *
     * @param x the upper left x position of the new screen area.
     * @param y the upper left y position of the new screen area.
     * @param width the width of the new screen area.
     * @param height the height of the new screen area.
     */
    public void setAreaManually(final int x, final int y, final int width, final int height) {
        setAreaManually(new Rectangle(x, y, width, height));
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
     * Saves the current content of bi into the passed File in .png format.
	 * It is recommended to save into %project_root%/data to guarantee the directory exists.
     *
     * @param f the fie to store to.
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void save(final File f) {
        try {
            new File("data/").mkdir();
            if (bi != null) {
                ImageIO.write(bi, "png", f);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GraphicsDevice[] getMonitors() {
        return monitors;
    }

    public Rectangle getArea() {
        return area;
    }

    public BufferedImage getBI() {
        return bi;
    }

    public void setMonitors(GraphicsDevice[] monitors) {
        this.monitors = monitors;
    }

    public void setArea(Rectangle area) {
        this.area = area;
    }

    public void setBI(BufferedImage bi) {
        this.bi = bi;
    }
}
