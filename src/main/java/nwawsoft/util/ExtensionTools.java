package nwawsoft.util;

import java.io.File;

public class ExtensionTools {
    private String jpeg = "jpeg";
    private String jpg = "jpg";
    private String gif = "gif";
    private String tiff = "tiff";
    private String tif = "tif";
    private String png = "png";

    public ExtensionTools() {

    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}