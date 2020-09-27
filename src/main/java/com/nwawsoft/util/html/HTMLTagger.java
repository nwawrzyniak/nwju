package com.nwawsoft.util.html;

/**
* Supplies Strings and functions to turn text into valid HTML.
*/
public class HTMLTagger {
  public static final String HTML_PRE = "<html>";
  public static final String LINK_PRE = HTML_PRE + "<a href=''>";
  public static final String HTML_POST = "</html>";
  public static final String LINK_POST = "</a>" + HTML_POST;

  /**
   * Surrounds any text with opening and closing HTML tags.
   *
   * @param text any sequence of characters. May be the empty String ("").
   * @return the text surrounded by HTML tags.
   */
  public static String toHTML(final String text) {
    return HTML_PRE + text + HTML_POST;
  }

  /**
   * Surrounds any text with opening and closing HTML tags and the link tag ("<a>").
   *
   * @param text any sequence of characters. May be the empty String ("").
   * @return the text surrounded by HTML tags.
   */
  public static String toLink(final String text) {
    return LINK_PRE + text + LINK_POST;
  }
}
