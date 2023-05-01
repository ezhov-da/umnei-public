package ru.ezhov.imagegenerator.api;

import javax.swing.SwingUtilities;
import java.awt.Desktop;
import java.io.File;
import java.nio.file.Files;
import java.util.Arrays;

class SwingImageGeneratorTest {


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            try {


                SwingImageGenerator swingImageGenerator = new SwingImageGenerator(
                        "header",
                        "footer",
                        Arrays.asList(
                                "2 + __ = 4", "2 + __ = 4",
                                "2 + __ = 4", "2 + __ = 4",
                                "2 + __ = 4", "2 + __ = 4",
                                "2 + __ = 4", "2 + __ = 4",
                                "2 + __ = 4", "2 + __ = 4",
                                "2 + __ = 4", "2 + __ = 4",
                                "2 + __ = 4", "2 + __ = 4",
                                "2 + __ = 4"
                        )
                );

                final byte[] generate = swingImageGenerator.generate();

//        assertEquals(41431, generate.length);

                final File test = File.createTempFile("test", ".png");

                Files.write(test.toPath(), generate);
                System.out.println(test.getAbsolutePath());

                Desktop.getDesktop().open(test);

            } catch (Exception e) {

            }
        });
    }
}