package ru.ezhov.imagegenerator;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ImageGeneratorApplication {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            setWhiteBackground();

            JFrame frame = new JFrame();

            GeneratorPanel generatorPanel = new GeneratorPanel("Примеры по математике", "умней.рус", Arrays.asList(
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4",
                    "2 + __ = 4"
            ));

            JToolBar jToolBar = new JToolBar();
            final JButton button = new JButton("Сделать снимок");

            button.addActionListener(e -> {
                BufferedImage bufferedImage = new BufferedImage(generatorPanel.getWidth(), generatorPanel.getHeight(), BufferedImage.TYPE_INT_RGB);

                Graphics2D g2d = bufferedImage.createGraphics();
                generatorPanel.print(g2d);
                g2d.dispose();

                try {
                    File file = File.createTempFile("img", "png");
                    ImageIO.write(bufferedImage, "PNG", file);
                    Desktop.getDesktop().open(file);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });

            jToolBar.add(button);


            frame.add(jToolBar, BorderLayout.NORTH);
            frame.add(new JScrollPane(generatorPanel), BorderLayout.CENTER);
            frame.setSize(700, 500);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void setWhiteBackground() {
        List<String> colorKeys = new ArrayList<String>();
        Set<Map.Entry<Object, Object>> entries = UIManager.getLookAndFeelDefaults().entrySet();
        for (Map.Entry entry : entries) {
            if (entry.getValue() instanceof Color) {

                String key = (String) entry.getKey();
                if (key.contains("background") || key.contains("Background")) {

                    colorKeys.add((String) entry.getKey());
                    UIManager.put(entry.getKey(), Color.WHITE);
                }
            }
        }

        // sort the color keys
//        Collections.sort(colorKeys);
//
//        // print the color keys
//        for (String colorKey : colorKeys) {
//            System.out.println(colorKey);
//        }
    }
}
