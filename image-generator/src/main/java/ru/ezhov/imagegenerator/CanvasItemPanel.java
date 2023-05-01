package ru.ezhov.imagegenerator;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Font;

public class CanvasItemPanel extends JPanel {
    public CanvasItemPanel(String value) {
        setLayout(new BorderLayout());

        final JLabel label = new JLabel(value);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        Font font = label.getFont();
        font = font.deriveFont(70F);
        label.setFont(font);

        add(label, BorderLayout.CENTER);
        label.doLayout();
        doLayout();
    }
}
