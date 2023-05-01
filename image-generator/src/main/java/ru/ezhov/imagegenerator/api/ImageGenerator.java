package ru.ezhov.imagegenerator.api;

public interface ImageGenerator {
    byte[] generate() throws ImageGeneratorException;
}
