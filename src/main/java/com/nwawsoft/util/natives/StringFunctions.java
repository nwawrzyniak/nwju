package com.nwawsoft.util.natives;

import com.nwawsoft.util.datastructures.StringList;
import com.nwawsoft.util.tools.DebugPrinter;

/**
 * Supplies functions for {@code String}s.
 */
public class StringFunctions {
  private final static String FILE_PROTOCOL_MARKER = "file:/";
  private final static char SMALL_A = 'a';
  private final static char SMALL_Z = 'z';
  private final static char BIG_A = 'A';
  private final static char BIG_Z = 'Z';
  private final static char ZERO = '0';
  private final static char NINE = '9';
  private final static char DOT = '.';
  private final static char SLASH = '/';
  private final static char UNDERSCORE = '_';
  private final static char COLON = ':';
  
  /**
   * Changes the case of the first character in a String to be upper case and all other characters in the String to
   * be lower case. Letters outside of a to z and A to Z remain unchanged..
   *
   * @param input the String to convert
   * @return the newly cased String.
   */
  public static String toOnlyFirstCharCapital(final String input) {
    if (input != null && !input.equals("")) {
      String outputString = input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
      DebugPrinter.dp("Result of toOnlyFirstCharCapital(\"" + input + "\"): \"" + outputString + "\"");
      return outputString;
    }
    DebugPrinter.dp("Result of toOnlyFirstCharCapital(\"" + input + "\"): \"\"");
    return "";
  }
  
  /**
   * Checks whether a String has no upper case letters except for the very first.
   *
   * @param input the String to check.
   * @return true if the first character is upper case or not a letter and every other character
   * is lower case or not a letter. Else false.
   */
  public static boolean isOnlyFirstCharCapital(final String input) {
    if (input.equals(toOnlyFirstCharCapital(input))) {
      DebugPrinter.dp("Result of isOnlyFirstCharCapital(\"" + input + "\"): true");
      return true;
    }
    DebugPrinter.dp("Result of isOnlyFirstCharCapital(\"" + input + "\"): false");
    return false;
  }
  
  /**
   * Checks whether a String contains at least one letter from a to z or A to Z.
   *
   * @param input the String to check
   * @return true if at least one letter was found. Else false.
   */
  public static boolean containsLetter(final String input) {
    if (input != null) {
      for (int i = 0; i < input.length(); i++) {
        if ((input.charAt(i) >= SMALL_A && input.charAt(i) <= SMALL_Z)
            || (input.charAt(i) >= BIG_A && input.charAt(i) <= BIG_Z)) {
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * Checks wether only Characters from 'a' to 'z' appear in a the String.
   *
   * @param input the String to check
   * @return true if no other Character than smaller case a to z is found
   */
  public static boolean isOnlyLowerCaseLetters(final String input) {
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) >= SMALL_A && input.charAt(i) <= SMALL_Z) {
        DebugPrinter.dp("Result of isOnlyLowerCaseLetters(\"" + input + "\"): " +
            "false");
        return false;
      }
    }
    DebugPrinter.dp("Result of isOnlyLowerCaseLetters(\"" + input + "\"): true");
    return true;
  }
  
  /**
   * Checks whether only Characters from 'A' to 'Z' appear in a the String.
   *
   * @param input the String to check
   * @return true if no other Character than upper case a to z is found
   */
  public static boolean isOnlyUpperCaseLetters(final String input) {
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) >= BIG_A && input.charAt(i) <= BIG_Z) {
        DebugPrinter.dp("Result of isOnlyUpperCaseLetters(\"" + input + "\"): " +
            "false");
        return false;
      }
    }
    DebugPrinter.dp("Result of isOnlyUpperCaseLetters(\"" + input + "\"): true");
    return true;
  }
  
  /**
   * Checks whether only digits from '0' to '9' appear in a the String.
   *
   * @param input the String to check
   * @return true if input contains no non-digit character.
   */
  public static boolean isOnlyDigits(final String input) {
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) >= ZERO && input.charAt(i) <= NINE) {
        DebugPrinter.dp("Result of isOnlyDigits(\"" + input + "\"): " + "false");
        return false;
      }
    }
    DebugPrinter.dp("Result of isOnlyDigits(\"" + input + "\"): true");
    return true;
  }
  
  /**
   * Checks whether a String only contains ['a'-'z'] and '_'.
   *
   * @param input the String to check.
   * @return true if only allowed Characters (ASCII 0x61 to 0x7A and 0x5F) appear in the String.
   */
  public static boolean isNormalized(final String input) {
    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (!((currentChar >= SMALL_A && currentChar <= SMALL_Z)
          || currentChar == UNDERSCORE)) {
        DebugPrinter.dp("Result of isNormalized(\"" + input + "\"): false");
        return false;
      }
    }
    DebugPrinter.dp("Result of isNormalized(\"" + input + "\"): true");
    return true;
  }
  
  /**
   * Checks whether a String only contains [a-z], [A-Z], [0-9], '.' and '_'
   *
   * @param input the String to check
   * @return true if only allowed Characters appear in the String
   */
  public static boolean isFileName(final String input) {
    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (!(((currentChar >= BIG_A && currentChar <= BIG_Z)
          || (currentChar >= SMALL_A && currentChar <= SMALL_Z)
          || (currentChar >= ZERO && currentChar <= NINE)
          || currentChar == DOT
          || currentChar == UNDERSCORE))
          && (!containsOnly(input, '.'))) {
        DebugPrinter.dp("Result of isFileName(\"" + input + "\"): false");
        return false;
      }
    }
    DebugPrinter.dp("Result of isFileName(\"" + input + "\"): true");
    return true;
  }
  
  /**
   * Checks whether a String only contains [a-z], [A-Z], [0-9], '/', '.' and '_'
   *
   * @param input the String to check
   * @return true if only allowed Characters appear in the String
   */
  public static boolean isFilePath(final String input) {
    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (!(((currentChar >= BIG_A && currentChar <= BIG_Z)
          || (currentChar >= SMALL_A && currentChar <= SMALL_Z)
          || (currentChar >= ZERO && currentChar <= NINE)
          || currentChar == DOT
          || currentChar == SLASH
          || currentChar == UNDERSCORE))
          && (!containsOnly(input, '.'))) {
        DebugPrinter.dp("Result of isFilePath(\"" + input + "\"): false");
        return false;
      }
    }
    DebugPrinter.dp("Result of isFilePath(\"" + input + "\"): true");
    return true;
  }
  
  /**
   * Checks whether a String only contains [a-z], [A-Z], [0-9], '/', '.' ':' and '_'
   *
   * @param input the String to check
   * @return true if only allowed Characters appear in the String
   */
  public static boolean isFileIdentifier(final String input) {
    for (int i = 0; i < input.length(); i++) {
      char currentChar = input.charAt(i);
      if (!(((currentChar >= BIG_A && currentChar <= BIG_Z)
          || (currentChar >= SMALL_A && currentChar <= SMALL_Z)
          || (currentChar >= ZERO && currentChar <= NINE)
          || currentChar == DOT
          || currentChar == SLASH
          || currentChar == UNDERSCORE
          || currentChar == COLON))
          && (!containsOnly(input, '.'))) {
        DebugPrinter.dp("Result of isFileIdentifier(\"" + input + "\"): false");
        return false;
      }
    }
    DebugPrinter.dp("Result of isFileIdentifier(\"" + input + "\"): true");
    return true;
  }
  
  /**
   * Checks whether a String contains nothing else than an arbitrary amount of the specified
   * character.
   *
   * @param string the String to check
   * @param match  the character to match against
   * @return true if only the specified character is included. Else false. Also returns false
   * when string is null.
   */
  public static boolean containsOnly(final String string, final char match) {
    if (string != null) {
      for (int i = 0; i < string.length(); i++) {
        if (!(string.charAt(i) == match)) {
          DebugPrinter.dp
              ("Result of containsOnly(\"" + string + "\", '" + match + "'): false");
          return false;
        }
      }
      DebugPrinter.dp("Result of containsOnly(\"" + string + "\", '" + match + "'): " +
          "true");
      return true;
    } else {
      DebugPrinter.dp("Result of containsOnly(null, '" + match + "'): false");
      return false;
    }
  }
  
  /**
   * Returns a String that is like it's input String with all white spaces removed.
   *
   * @param input some String
   * @return the new String without
   */
  public static String removeWhiteSpaces(final String input) {
    String newString = input.replaceAll("\\s", "");
    DebugPrinter.dp("Result of removeWhiteSpaces(\"" + input + "\"): \"" + newString
        + "\"");
    return newString;
  }
  
  /**
   * Checks whether two Strings are equal if all white spaces are removed and upper/lower case
   * is ignored.
   *
   * @param firstString  some String
   * @param secondString another or equal String
   * @return true if Strings are similar, else false.
   */
  public static boolean equalsStandardized(final String firstString, final String secondString) {
    boolean output = removeWhiteSpaces(firstString).toLowerCase().equals(removeWhiteSpaces
        (secondString).toLowerCase());
    DebugPrinter.dp("Result of equalsStandardized(\"" + firstString + "\", \""
        + secondString + "\"): " + output);
    return output;
  }
  
  public static String cutPathProtocol(final String input) {
    String outputString;
    if (input.startsWith(FILE_PROTOCOL_MARKER)) {
      outputString = input.substring(FILE_PROTOCOL_MARKER.length());
      DebugPrinter.dp("Result of cutPathProtocol(\"" + input + "\"): \"" +
          outputString + "\"");
      return outputString;
    } else {
      DebugPrinter.dp("Result of cutPathProtocol(\"" + input + "\"): \"" + input
          + "\"");
      return input;
    }
  }
  
  /**
   * Checks whether there is at least one lower case character somewhere in the specified String.
   *
   * @param input the String to check
   * @return true if a lower case character was found. Else false.
   */
  public static boolean containsLowerCaseCharacters(final String input) {
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < inputArray.length; i++) {
      if (CharFunctions.isLowerCaseLetter(inputArray[i])) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Checks whether there is at least one upper case character somewhere in the specified String.
   *
   * @param input the String to check
   * @return true if an upper case character was found. Else false.
   */
  public static boolean containsUpperCaseCharacters(final String input) {
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < inputArray.length; i++) {
      if (CharFunctions.isUpperCaseLetter(inputArray[i])) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Checks whether there is at least one special character somewhere in the specified String.
   * A special character is every non-English-standard-letter (A-Z, a-z) or digit (0-9).
   *
   * @param input the String to check
   * @return true if a special character was found. Else false.
   */
  public static boolean containsSpecialCharacters(final String input) {
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < inputArray.length; i++) {
      if (CharFunctions.isSpecialCharacter(inputArray[i])) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Checks whether there is at least one digit somewhere in the specified String.
   *
   * @param input the String to check
   * @return true if a digit was found. Else false.
   */
  public static boolean containsDigits(final String input) {
    char[] inputArray = input.toCharArray();
    for (int i = 0; i < inputArray.length; i++) {
      if (CharFunctions.isDigit(inputArray[i])) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Checks whether a String "string" starts with any substring in a specified StringList "matchList".
   *
   * @param string    the String to check.
   * @param matchList the list of possible Strings string could start with.
   * @return true if string starts with any String in matchList. Else false.
   */
  public static boolean startsWithAny(final String string, final StringList matchList) {
    matchList.toFirst();
    while (matchList.hasAccess()) {
      if (string.startsWith((String) matchList.getObject())) {
        return true;
      }
      matchList.next();
    }
    return false;
  }
  
  /**
   * Returns the index of the first digit (0-9) in the input String.
   * Returns -1 if the String does not contain a digit.
   *
   * @param input any String.
   * @return the index of the first digit in 'input' or -1 if there is none.
   */
  public static int indexOfFirstDigit(final String input) {
    int index = -1;
    for (int i = 0; i < input.length(); i++) {
      if (CharFunctions.isNumeric(input.charAt(i))) {
        index = i;
        break;
      }
    }
    return index;
  }
}
