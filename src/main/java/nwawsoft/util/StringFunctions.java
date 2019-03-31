package nwawsoft.util;

/**
 * Created by Ernst on 22.06.2017.
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
     * @param string the String to convert
     * @return the newly cased String.
     */
    public static String toOnlyFirstCharCapital(String string) {
        String outputString = string;
        if (outputString != null && !outputString.equals("")) {
            outputString = string.toLowerCase();
            char firstChar = outputString.charAt(0);
            if (firstChar >= SMALL_A && firstChar <= SMALL_Z) {
                outputString = ("" + firstChar).toUpperCase() + string.substring(1);
            }
            DebugPrinter.dp("Result of toOnlyFirstCharCapital(\"" + string + "\"): \"" +
                    outputString + "\"");
            return outputString;
        }
        DebugPrinter.dp("Result of toOnlyFirstCharCapital(\"" + string + "\"): \"\"");
        return "";
    }

    /**
     * Checks whether a String has no upper case letters except for the very first.
     *
     * @param string the String to check.
     * @return true if the first character is upper case or not a letter and every other character
     * is lower case or not a letter. Else false.
     */
    public static boolean isOnlyFirstCharCapital(String string) {
        if (string.equals(toOnlyFirstCharCapital(string))) {
            DebugPrinter.dp("Result of isOnlyFirstCharCapital(\"" + string + "\"): true");
            return true;
        }
        DebugPrinter.dp("Result of isOnlyFirstCharCapital(\"" + string + "\"): false");
        return false;
    }

    /**
     * Checks whether a String contains at least one letter from a to z or A to Z.
     *
     * @param string the String to check
     * @return true if at least one letter was found. Else false.
     */
    public static boolean containsLetter(String string) {
        if (string != null) {
            for (int i = 0; i < string.length(); i++) {
                if ((string.charAt(i) >= SMALL_A && string.charAt(i) <= SMALL_Z)
                        || (string.charAt(i) >= BIG_A && string.charAt(i) <= BIG_Z)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks wether only Characters from 'a' to 'z' appear in a the String.
     *
     * @param string the String to check
     * @return true if no other Character than smaller case a to z is found
     */
    public static boolean isOnlyLowerCaseLetters(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= SMALL_A && string.charAt(i) <= SMALL_Z) {
                DebugPrinter.dp("Result of isOnlyLowerCaseLetters(\"" + string + "\"): " +
                        "false");
                return false;
            }
        }
        DebugPrinter.dp("Result of isOnlyLowerCaseLetters(\"" + string + "\"): true");
        return true;
    }

    /**
     * Checks wether only Characters from 'A' to 'Z' appear in a the String.
     *
     * @param string the String to check
     * @return true if no other Character than upper case a to z is found
     */
    public static boolean isOnlyUpperCaseLetters(String string) {
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) >= BIG_A && string.charAt(i) <= BIG_Z) {
                DebugPrinter.dp("Result of isOnlyUpperCaseLetters(\"" + string + "\"): " +
                        "false");
                return false;
            }
        }
        DebugPrinter.dp("Result of isOnlyUpperCaseLetters(\"" + string + "\"): true");
        return true;
    }

    /**
     * Checks whether a String only contains [a-z] and '_'
     *
     * @param string the String to check
     * @return true if only allowed Characters appear in the String
     */
    public static boolean isNormalized(String string) {
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (!((currentChar >= SMALL_A && currentChar <= SMALL_Z)
                    || currentChar == UNDERSCORE)) {
                DebugPrinter.dp("Result of isNormalized(\"" + string + "\"): false");
                return false;
            }
        }
        DebugPrinter.dp("Result of isNormalized(\"" + string + "\"): true");
        return true;
    }

    /**
     * Checks whether a String only contains [a-z], [A-Z], [0-9], '.' and '_'
     *
     * @param string the String to check
     * @return true if only allowed Characters appear in the String
     */
    public static boolean isFileName(String string) {
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (!(((currentChar >= BIG_A && currentChar <= BIG_Z)
                    || (currentChar >= SMALL_A && currentChar <= SMALL_Z)
                    || (currentChar >= ZERO && currentChar <= NINE)
                    || currentChar == DOT
                    || currentChar == UNDERSCORE))
                    && (!containsOnly(string, '.'))) {
                DebugPrinter.dp("Result of isFileName(\"" + string + "\"): false");
                return false;
            }
        }
        DebugPrinter.dp("Result of isFileName(\"" + string + "\"): true");
        return true;
    }

    /**
     * Checks whether a String only contains [a-z], [A-Z], [0-9], '/', '.' and '_'
     *
     * @param string the String to check
     * @return true if only allowed Characters appear in the String
     */
    public static boolean isFilePath(String string) {
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (!(((currentChar >= BIG_A && currentChar <= BIG_Z)
                    || (currentChar >= SMALL_A && currentChar <= SMALL_Z)
                    || (currentChar >= ZERO && currentChar <= NINE)
                    || currentChar == DOT
                    || currentChar == SLASH
                    || currentChar == UNDERSCORE))
                    && (!containsOnly(string, '.'))) {
                DebugPrinter.dp("Result of isFilePath(\"" + string + "\"): false");
                return false;
            }
        }
        DebugPrinter.dp("Result of isFilePath(\"" + string + "\"): true");
        return true;
    }

    /**
     * Checks whether a String only contains [a-z], [A-Z], [0-9], '/', '.' ':' and '_'
     *
     * @param string the String to check
     * @return true if only allowed Characters appear in the String
     */
    public static boolean isFileIdentifier(String string) {
        for (int i = 0; i < string.length(); i++) {
            char currentChar = string.charAt(i);
            if (!(((currentChar >= BIG_A && currentChar <= BIG_Z)
                    || (currentChar >= SMALL_A && currentChar <= SMALL_Z)
                    || (currentChar >= ZERO && currentChar <= NINE)
                    || currentChar == DOT
                    || currentChar == SLASH
                    || currentChar == UNDERSCORE
                    || currentChar == COLON))
                    && (!containsOnly(string, '.'))) {
                DebugPrinter.dp("Result of isFileIdentifier(\"" + string + "\"): false");
                return false;
            }
        }
        DebugPrinter.dp("Result of isFileIdentifier(\"" + string + "\"): true");
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
    public static boolean containsOnly(String string, char match) {
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
     * @param string some String
     * @return the new String without
     */
    public static String removeWhiteSpaces(String string) {
        String newString = string.replaceAll("\\s", "");
        DebugPrinter.dp("Result of removeWhiteSpaces(\"" + string + "\"): \"" + newString
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
    public static boolean equalsStandardized(String firstString, String secondString) {
        boolean output = removeWhiteSpaces(firstString).toLowerCase().equals(removeWhiteSpaces
                (secondString).toLowerCase());
        DebugPrinter.dp("Result of equalsStandardized(\"" + firstString + "\", \""
                + secondString + "\"): " + output);
        return output;
    }

    public static String cutPathProtocol(String string) {
        String outputString;
        if (string.startsWith(FILE_PROTOCOL_MARKER)) {
            outputString = string.substring(FILE_PROTOCOL_MARKER.length());
            DebugPrinter.dp("Result of cutPathProtocol(\"" + string + "\"): \"" +
                    outputString + "\"");
            return outputString;
        } else {
            DebugPrinter.dp("Result of cutPathProtocol(\"" + string + "\"): \"" + string
                    + "\"");
            return string;
        }
    }
}
