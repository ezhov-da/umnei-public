package ru.ezhov.umnei.utils;

public class MdcAssistant {
    private static final String DEFAULT_DELIMITER = ";";
    private final String data;
    private String delimiter = DEFAULT_DELIMITER;

    private MdcAssistant(String data) {
        this.data = data;
    }

    private MdcAssistant(String data, String delimiter) {
        this.data = data;
        this.delimiter = delimiter;
    }


    public static MdcAssistant create(String data) {
        return new MdcAssistant(data);
    }

    public String build(){
        return this.data;
    }

    public MdcAssistant and(String data) {
        return new MdcAssistant(String.join(delimiter, this.data, data));
    }
}
