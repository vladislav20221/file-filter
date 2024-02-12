package src;

import src.filter.FileFilter;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Application {
    public static final Properties properties = new Properties();

    public static void main(String ... args) {
        try (final FileReader in = new FileReader("config.properties")) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        final FileFilter filter = new FileFilter();
        filter.scanning();
    }

}
