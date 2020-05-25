package com.nwawsoft.util.natives;

/**
 * Supplies functions for manipulating and working with the native data type 'char'.
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
    return (c >= '0' && c <= '9') || c == '.' || c == ',';
  }

  /**
   * Returns whether a specified character is one of the 26 standard lower case 
   * letters from 'a' to 'z'.
   * 
   * @param c a char to check.
   * @return true if c is a lower case letter. Else false.
   */
  public static boolean isLowerCaseLetter(final char c) {
    return c >= 'a' && c <= 'z';
  }

  /**
   * Returns whether a specified character is one of the 26 standard upper case 
   * letters from 'A' to 'Z'.
   * 
   * @param c a char to check.
   * @return true if c is a upper case letter. Else false.
   */
  public static boolean isUpperCaseLetter(final char c) {
    return c >= 'A' && c <= 'Z';
  }

  /**
   * Returns whether a specified character is a digit between 0 and 9.
   * 
   * @param c a char to check.
   * @return true if c is a digit. Else false.
   */
  public static boolean isDigit(final char c) {
    return c >= '0' && c <= '9';
  }

  /**
   * Returns true, if the specified character is neither a lower case letter, an 
   * upper case letter or a digit. For more info
   * @see isLowerCaseLetter @see isUpperCaseLetter @see isDigit
   * 
   * @param c a char to check.
   * @return true if c is neither an upper case or lower case letter or a digit.
   * 			False if it is.
   */
  public static boolean isSpecialCharacter(final char c) {
    return (!isUpperCaseLetter(c) && !isLowerCaseLetter(c) && !isDigit(c));
  }
}
