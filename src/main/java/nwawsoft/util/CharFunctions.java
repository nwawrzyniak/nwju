package nwawsoft.util;

/**
 * Supplies functions for manipulating and working with tha native data type 'char'.
 */
public class CharFunctions {
  /**
   * Checks whether a specified character is either a digit from 0 to 9 or a '.' or ','.
   *
   * @param c a char to check.
   * @return true if c is a digit, dot or commma. Else false.
   */
  public static boolean isNumeric(final char c)
  {
    return ((int) c >= 48 && (int) c <= 57) || (int) c == 44 || (int) c == 46;
  }
}