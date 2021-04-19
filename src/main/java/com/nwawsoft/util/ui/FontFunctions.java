package com.nwawsoft.util.ui;

import com.nwawsoft.util.natives.StringFunctions;

import java.awt.*;

/**
 * Supplies functions to work easier with {@code java.awt.Font}s.
 * This class features so-called {@code fontString}s in the format {@code "NAME;STYLE;SIZE"}.
 */
public class FontFunctions {
  /**
   * Creates a {@code Font} from a fontString.
   *
   * @param fontString a String in the format "NAME;STYLE;SIZE".
   * @return a corresponding java.awt.Font object.
   */
  public static Font parseFont(final String fontString) {
    return new Font(getName(fontString), getStyle(fontString), getSize(fontString));
  }
  
  /**
   * Creates a fontString from a font object.
   *
   * @param font any font object.
   * @return a String in the format "NAME;STYLE;SIZE".
   */
  public static String toFontString(final Font font) {
    return font.getName() + ";" + font.getStyle() + ";" + font.getSize();
  }
  
  /**
   * Returns the NAME part of a fontString.
   *
   * @param fontString a String in the format "NAME;STYLE;SIZE".
   * @return the NAME part of the fontString.
   */
  public static String getName(final String fontString) {
    return fontString.substring(0, StringFunctions.indexOfFirstDigit(fontString) - 1);
  }
  
  /**
   * Returns the STYLE part of a fontString.
   *
   * @param fontString a String in the format "NAME;STYLE;SIZE".
   * @return the STYLE part of the fontString.
   */
  
  public static int getStyle(final String fontString) {
    return Integer.parseInt(fontString.substring(StringFunctions.indexOfFirstDigit(fontString),
        fontString.lastIndexOf(";")));
  }
  
  /**
   * Returns the SIZE part of a fontString.
   *
   * @param fontString a String in the format "NAME;STYLE;SIZE".
   * @return the SIZE part of the fontString.
   */
  public static int getSize(final String fontString) {
    return Integer.parseInt(fontString.substring(fontString.lastIndexOf(";") + 1));
  }
}
