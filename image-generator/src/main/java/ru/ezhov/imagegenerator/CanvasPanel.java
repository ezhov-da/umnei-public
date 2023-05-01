package ru.ezhov.imagegenerator;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.List;

public class CanvasPanel extends JPanel {

    public CanvasPanel(List<String> values) {

        final Dimension dimension = new Dimension((int) A4Size.WIDTH, (int) A4Size.HEIGHT);

        setLayout(new GridLayout(5, 3));

        setSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);

        int counter = 0;
        for (String v : values) {
            if (counter == 15) {
                break;
            }
            counter++;
            add(new CanvasItemPanel(v));
        }

        doLayout();
    }
}
