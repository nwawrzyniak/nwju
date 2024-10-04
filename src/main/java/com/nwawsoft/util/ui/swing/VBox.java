package com.nwawsoft.util.ui.swing;

import javax.swing.*;

public class VBox extends Box {
    private VBox(int axis) {
        super(axis);
    }

    public VBox() {
        this(BoxLayout.Y_AXIS);
    }

    public void addSpacer(final int height) {
        this.add(createVerticalStrut(height));
    }
}
