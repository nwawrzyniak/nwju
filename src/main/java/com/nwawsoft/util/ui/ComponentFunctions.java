package com.nwawsoft.util.ui;

import com.nwawsoft.util.tools.DebugPrinter;

import java.awt.*;

/**
 * Supplies functions for {@code java.awt.Component}s.
 */
public class ComponentFunctions {
  
  /**
   * Centers a {@code java.awt.Component} to the center of the screen.
   *
   * @param c any object of type Component.
   */
  public static void center(final Component c) {
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - c.getSize().width) / 2;
    DebugPrinter.dp("d.width: " + d.width +
        ", c.getSize().width: " +
        c.getSize().width + ", x: " + x);
    int y = (d.height - c.getSize().height) / 2;
    DebugPrinter.dp("d.height: " + d.height +
        ", c.getSize().height: " +
        c.getSize().height + ", y: " + y);
    c.setLocation(x, y);
  }
}
