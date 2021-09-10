package com.nwawsoft.util.tools;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.IOException;

/**
 * Supplies functions to copy to and paste from the clipboard.
 * Can paste to all awt, swing and JavaFX components that can handle text.
 */
public class ClipboardManager {
    /**
     * Writes a String to the system's/user's clipboard.
     *
     * @param clipboardText the text to copy to the clipboard
     */
    public static void copyToClipboard(final String clipboardText) {
        getSystemClipboard().setContents(new StringSelection(clipboardText), null);
    }

    /**
     * Returns the Clipboard object that Java keeps synchronized with the OS's clipboard.
     * This is just a wrapper to not call Toolkit explicitly.
     *
     * @return the Clipboard object
     */
    public static Clipboard getSystemClipboard() {
        return Toolkit.getDefaultToolkit().getSystemClipboard();
    }

 /**
  * Return the content of the system's/user's clipboard as a String.
  * If anything fails this returns an empty String.
  *
  * @return a String representing the content of the clipboard
  */
 public String readSystemClipboard() {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(this);
        if (t == null)
            return "";
        else {
            try {
                return (String) t.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    /**
     * Pastes the content of the system clipboard to the specified JTextComponent.
     *
     * @param jtc a JTextComponent
     */
    public void pasteClipboardTo(final JTextComponent jtc) {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(this);
        if (t == null)
            return;
        try {
            jtc.setText((String) t.getTransferData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Pastes the content of the system clipboard to the specified TextComponent.
     *
     * @param tc a TextComponent
     */
    public void pasteClipboardTo(final TextComponent tc) {
        Transferable t = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(this);
        if (t == null)
            return;
        try {
            tc.setText((String) t.getTransferData(DataFlavor.stringFlavor));
        } catch (UnsupportedFlavorException | IOException e) {
            e.printStackTrace();
        }
    }
}
