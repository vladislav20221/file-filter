package src.enums;

public enum DataType {
    STRING("\\w+"),
    INTEGER("[+-]?\\d+"),
    FLOAT("[+-]?\\d+\\.\\d+");

    private final String pattern;

    DataType(final String pattern) {
        this.pattern = pattern;
    }

    public String pattern() {
        return this.pattern;
    }

}
