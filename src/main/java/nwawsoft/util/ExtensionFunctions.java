package nwawsoft.util;

import java.io.File;

/**
 * Provides functions to work with file extensions.
 */
public class ExtensionFunctions {
    /**
     * Returns the file extension of a specified File as a String (without the leading '.').
     * If there is no file extension an empty String is returned.
     *
     * @param f a File object.
     * @return the file extension of f as a String without the leading dot or an empty String if there is none.
     */
    public static String getExtension(final File f) {
        String ext = null;
        String s = f.getName();
        if (s.contains(".")) {
            int i = s.lastIndexOf('.');
            if (i > 0 && i < s.length() - 1) {
                ext = s.substring(i + 1).toLowerCase();
            }
            return ext;
        }
        DebugPrinter.dp("No file extension found. Returning empty String.");
        return "";
    }
}
