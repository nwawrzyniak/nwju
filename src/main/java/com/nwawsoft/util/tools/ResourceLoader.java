package com.nwawsoft.util.tools;

import java.io.File;
import java.net.URL;

/**
 * Provides functions to load information from a/the resource directory.
 */
public class ResourceLoader {
    /**
     * Returns a File[] containing the full names of all files in the specified directory.
     *
     * @param directory any directory within the resource directory without a leading or ending '/' (slash).
     * @return a File[] containing the full names of all files in the specified directory. null if the URL matching the
     *          directory was not found.
     */
    public static File[] getResourceFolderFiles (final String directory) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        URL url = cl.getResource(directory);
        String path = null;
        if (url != null) {
            path = url.getPath();
        }
        if (path != null) {
            return new File(path).listFiles();
        } else {
            return null;
        }
    }

    /**
     * Returns an array containing the names of all files in a specified directory within the resource directory.
     * If noFileEnding is true file extensions (everything from and including the last '.' [dot] to the end of the
     * file name) are cut away from the results.
     *
     * @param directory any directory within the resource directory (without leading or trailing '/' [slash]).
     * @param noFileEnding true if the file ending (including the '.' [dot]) shall be cut. Else false.
     * @return an array containing the names of all files in the specified resource directory.
     */
    public static String[] getFileNames(final String directory, final boolean noFileEnding) {
        File[] files = getResourceFolderFiles(directory);
        String[] names = null;
        if (files != null) {
            names = new String[files.length];
            for (int i = 0; i < files.length; i++) {
                if (noFileEnding) {
                    names[i] = files[i].getName().substring(0, files[i].getName().lastIndexOf("."));
                } else {
                    names[i] = files[i].getName();
                }
            }
        }
        return names;
    }

    /**
     * Wrapper for getFileNames(String, boolean). Always cuts file endings.
     *
     * @param directory any directory within the resource directory.
     * @return an array containing the names of all files in the specified resource directory wit no file endings.
     */
    public static String[] getFileNames(final String directory) {
        return getFileNames(directory, true);
    }
}
