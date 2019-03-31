package nwawsoft.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Ernst on 18.07.2017.
 */
public class PathFunctions {
    /**
     * Prints the current working directory with two methods.
     */
    public static void printCurrentWorkingDirectory() {
        try {
            System.out.println("Result of \"File(\".\").getCanonicalPath()\":\n" + new File(".").getCanonicalPath() + "\n");
        } catch (IOException e) {
            System.err.println("\"File(\".\").getCanonicalPath()\" failed.\nResults of \"System.getProperty(\"user.dir\")\" follow after stack trace.");
            e.printStackTrace();
        }
        System.out.println("Result of \"System.getProperty(\"user.dir\")\":\n" + System.getProperty("user.dir"));
    }

    /**
     * Original code: https://www.java-forum.org/thema/wie-kann-ich-schauen-ob-ein-ordner-vorhanden-ist.568/
     *
     * @param dirName the name of the directory."
     * @return true if directory exists, else false
     */
    public static boolean directoryExists(String dirName) {
        File stats = new File(dirName);
        if (stats.exists()) {
            return stats.isDirectory();
        }
        return false;
    }

    /**
     * Wrapper for using the ClassLoader.
     *
     * @param path the path of a file or directory beginning at the resource root. (e.g. "profiles/file_3.txt")
     */
    public URL loadRes(String path) {
        return getClass().getClassLoader().getResource(path);
    }
}
