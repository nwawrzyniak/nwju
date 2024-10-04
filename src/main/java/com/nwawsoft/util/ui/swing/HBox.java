package com.nwawsoft.util.ui.swing;

import javax.swing.*;

public class HBox extends Box {
    private HBox(int axis) {
        super(axis);
    }

    public HBox() {
        this(BoxLayout.X_AXIS);
    }

    public void addSpacer(final int width) {
        this.add(createHorizontalStrut(width));
    }
}
