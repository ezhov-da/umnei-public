package ru.ezhov.imagegenerator.api;

import ru.ezhov.imagegenerator.GeneratorPanel;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class SwingImageGenerator implements ImageGenerator {
    private final String header;
    private final String footer;
    private final List<String> values;

    public SwingImageGenerator(String header, String footer, List<String> values) {
        this.header = header;
        this.footer = footer;
        this.values = values;
    }

    @Override
    public byte[] generate() throws ImageGeneratorException {
        setWhiteBackground();

        JFrame frame = new JFrame();
        GeneratorPanel generatorPanel = new GeneratorPanel(header, footer, values);
        frame.add(generatorPanel);
        frame.pack();

        BufferedImage bufferedImage = new BufferedImage(generatorPanel.getWidth(), generatorPanel.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = bufferedImage.createGraphics();
        generatorPanel.print(g2d);
        g2d.dispose();

        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "PNG", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new ImageGeneratorException("Ошибка создания изображения", e);
        }
    }

    private void setWhiteBackground() {
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
    }
}