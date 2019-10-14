package nwawsoft.util;

/**
 * Contains the Unicode codes of the German mutated vowels. Using these codes in a String over the regular characters
 * gives your program the possibility to work with different encodings than UTF-8 without the characters turning
 * jibberish. The Strings of this class should be imported static [e.g. import static nwawsoft.util.MutatedVowels.*;].
 */
public class MutatedVowels {
    /**
     * Ä
     */
    public static final String aE = "\u00c4";

    /**
     * ä
     */
    public static final String ae = "\u00e4";

    /**
     * Ö
     */
    public static final String oE = "\u00d6";

    /**
     * ö
     */
    public static final String oe = "\u00f6";

    /**
     * Ü
     */
    public static final String uE = "\u00dc";

    /**
     * ü
     */
    public static final String ue = "\u00fc";

    /**
     * ß
     */
    public static final String ss = "\u00df";
}
