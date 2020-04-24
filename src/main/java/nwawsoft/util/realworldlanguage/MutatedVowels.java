package nwawsoft.util.realworldlanguage;

/**
 * Contains the Unicode codes of the German mutated vowels. Using these codes in a String over the regular characters
 * gives your program the possibility to work with different encodings than UTF-8 without the characters turning
 * jibberish. The Strings of this class should be imported static [e.g. import static nwawsoft.util.language.MutatedVowels.*;].
 */
public class MutatedVowels {
    /**
     * Ä
     */
    public static final char aE = '\u00c4';

    /**
     * ä
     */
    public static final char ae = '\u00e4';

    /**
     * Ö
     */
    public static final char oE = '\u00d6';

    /**
     * ö
     */
    public static final char oe = '\u00f6';

    /**
     * Ü
     */
    public static final char uE = '\u00dc';

    /**
     * ü
     */
    public static final char ue = '\u00fc';

    /**
     * ß
     */
    public static final char ss = '\u00df';
}
