package nwawsoft.util.file;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Provides functions for easier handling of and working with files, directories and paths.
 */
public class PathFunctions {
    /**
     * Prints the current working directory using File.getCanonicalPath() and System.getProperty("user.dir").
     */
    public static void printCurrentWorkingDirectory() {
        try {
            System.out.println("Result of \"File(\".\").getCanonicalPath()\":");
            System.out.println("" + new File(".").getCanonicalPath());
        } catch (IOException e) {
            System.err.println("\"File(\".\").getCanonicalPath()\" failed.\nResults of \"System.getProperty" +
                    "(\"user.dir\")\" follow after stack trace.");
            e.printStackTrace();
        }
        System.out.println("Result of \"System.getProperty(\"user.dir\")\":\n" + System.getProperty("user.dir"));
    }

    /**
     * Returns whether a specified directory exists.
     *
     * @param dirName the name of the directory.
     * @return true if directory exists, else false.
     */
    public static boolean existsDir(final String dirName) {
        File dir = new File(dirName);
        if (dir.exists()) {
            return dir.isDirectory();
        }
        return false;
    }

    /**
     * Returns whether a specified directory exists.
     *
     * @param dir the directory as an object of type {@code File}.
     * @return true if directory exists, else false.
     */
    public static boolean existsDir(final File dir) {
        if (dir.exists()) {
            return dir.isDirectory();
        }
        return false;
    }

    /**
     * Wrapper for obtaining a URL using the ClassLoader to load a resource.
     *
     * @param path the path of a file or directory beginning at the resource root. (e.g. "profiles/file_3.txt").
     * @return the URL of the file specified by the path.
     */
    public URL loadRes(final String path) {
        return getClass().getClassLoader().getResource(path);
    }
}
