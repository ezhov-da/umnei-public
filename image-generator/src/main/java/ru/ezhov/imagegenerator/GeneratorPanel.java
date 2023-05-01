package ru.ezhov.imagegenerator;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;

public class GeneratorPanel extends JPanel {
    public GeneratorPanel(String header, String footer, List<String> values) {
        final Dimension dimension = new Dimension((int) A4Size.WIDTH, (int) A4Size.HEIGHT);
        setSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setPreferredSize(dimension);

        setLayout(new BorderLayout());
        JLabel labelHeader = new JLabel(header);
        labelHeader.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 20));

        Font fontLabelHeader = labelHeader.getFont();
        fontLabelHeader = fontLabelHeader.deriveFont(100F);
        labelHeader.setFont(fontLabelHeader);

        JLabel labelFooter = new JLabel(footer);
        labelFooter.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 50));
        labelFooter.setHorizontalAlignment(SwingConstants.RIGHT);

        Font fontLabelFooter = labelFooter.getFont();
        fontLabelFooter = fontLabelFooter.deriveFont(45F);
        labelFooter.setFont(fontLabelFooter);

        add(labelHeader, BorderLayout.NORTH);
        add(new CanvasPanel(values), BorderLayout.CENTER);
        add(labelFooter, BorderLayout.SOUTH);
    }
}
