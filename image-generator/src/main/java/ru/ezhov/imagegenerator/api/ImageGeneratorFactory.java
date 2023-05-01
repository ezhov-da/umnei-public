package ru.ezhov.imagegenerator.api;

import java.util.List;

public abstract class ImageGeneratorFactory {

    private ImageGeneratorFactory() {
    }

    public static ImageGenerator getInstance(String header, String footer, List<String> values) {
        return new SwingImageGenerator(header, footer, values);
    }
}
