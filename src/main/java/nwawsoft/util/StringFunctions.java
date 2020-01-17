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
    public static String toOnlyFirstCharCapital(final String string) {
        if (string != null && !string.equals("")) {
            String outputString = string.substring(0,1).toUpperCase() + string.substring(1).toLowerCase();
            DebugPrinter.dp("Result of toOnlyFirstCharCapital(\"" + string + "\"): \"" + outputString + "\"");
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
    public static boolean isOnlyFirstCharCapital(final String string) {
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
    public static boolean containsLetter(final String string) {
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
    public static boolean isOnlyLowerCaseLetters(final String string) {
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
     * Checks whether only Characters from 'A' to 'Z' appear in a the String.
     *
     * @param string the String to check
     * @return true if no other Character than upper case a to z is found
     */
    public static boolean isOnlyUpperCaseLetters(final String string) {
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
     * Checks whether
     * @param pInput
     * @return
     */
    public boolean isOnlyUpperCase(final String pInput) {
        char[] inputChars = pInput.toCharArray();
        for (int i = 0; i < inputChars.length; i++) {
            if (!Character.isUpperCase(inputChars[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks whether a String only contains ['a'-'z'] and '_'.
     *
     * @param string the String to check.
     * @return true if only allowed Characters (ASCII 0x61 to 0x7A and 0x5F) appear in the String.
     */
    public static boolean isNormalized(final String string) {
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
    public static boolean isFileName(final String string) {
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
    public static boolean isFilePath(final String string) {
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
    public static boolean isFileIdentifier(final String string) {
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
     * @param string some String
     * @return the new String without
     */
    public static String removeWhiteSpaces(final String string) {
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
    public static boolean equalsStandardized(final String firstString, final String secondString) {
        boolean output = removeWhiteSpaces(firstString).toLowerCase().equals(removeWhiteSpaces
                (secondString).toLowerCase());
        DebugPrinter.dp("Result of equalsStandardized(\"" + firstString + "\", \""
                + secondString + "\"): " + output);
        return output;
    }

    public static String cutPathProtocol(final String string) {
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
}
