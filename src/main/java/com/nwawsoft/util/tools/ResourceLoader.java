package com.nwawsoft.util.tools;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Provides functions to load information from a/the resource directory.
 */
public class ResourceLoader {
    /**
     * Returns a File[] containing the full names of all files in the specified directory.
     *
     * @param directory any directory within the resource directory without a leading or ending '/' (slash).
     * @return a File[] containing the full names of all files in the specified directory. null if the URL matching the
     * directory was not found.
     */
    public static File[] getResourceFolderFiles(final String directory) {
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
     * @param directory    any directory within the resource directory (without leading or trailing '/' [slash]).
     * @param noFileEnding true if the file ending (including the '.' [dot]) shall be cut. Else false.
     * @param isInJar      whether the resources are in a jar.
     * @return an array containing the names of all files in the specified resource directory. null if something went
     * wrong.
     */
    public static String[] getFileNames(final String directory, final boolean noFileEnding, final boolean isInJar) {
        String[] fileNames = null;
        if (!isInJar) {
            File[] files = getResourceFolderFiles(directory);
            if (files != null) {
                fileNames = new String[files.length];
                for (int i = 0; i < files.length; i++) {
                    if (noFileEnding) {
                        fileNames[i] = files[i].getName().substring(0, files[i].getName().lastIndexOf("."));
                    } else {
                        fileNames[i] = files[i].getName();
                    }
                }
            }
            return fileNames;
        } else {
            List<String> fileNamesList = new ArrayList<>();
            String dirNameJar = directory + "/";
            String path;
            URL url = Thread.currentThread().getContextClassLoader().getResource(directory);
            if (url != null) {
                path = url.getPath();
                String jarPath = path.substring(5, path.indexOf("!"));
                try (JarFile jar = new JarFile(URLDecoder.decode(jarPath, StandardCharsets.UTF_8.name()))) {
                    Enumeration<JarEntry> entries = jar.entries();
                    while (entries.hasMoreElements()) {
                        JarEntry entry = entries.nextElement();
                        String name = entry.getName();
                        if (name.startsWith(dirNameJar) && !dirNameJar.equals(name)) {
                            URL resource = Thread.currentThread().getContextClassLoader().getResource(name);
                            if (resource != null) {
                                fileNamesList.add(resource.toString());
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                fileNames = new String[fileNamesList.size()];
                int counter = 0;
                for (String fileName : fileNames) {
                    fileNames[counter] = fileName;
                    counter++;
                }
                return fileNames;
            }
        }
        return null;
    }
}
