package nwawsoft.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * Supplies functions to work with BufferedImages.
 */
public class BufferedImageFunctions {

    public BufferedImageFunctions() {}

    /**
     * Clones a given BufferedImage and returns an exact copy of it.
     *
     * @param bi any BufferedImage.
     * @return an exact copy of the input BufferedImage.
     */
    public static BufferedImage clone(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        WritableRaster raster = bi.copyData(bi.getRaster().createCompatibleWritableRaster());
        return new BufferedImage(cm, raster, cm.isAlphaPremultiplied(), null)
                .getSubimage(0, 0, bi.getWidth(), bi.getHeight());
    }
}
