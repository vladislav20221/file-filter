package src;

import src.filter.FileFilter;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.IntStream;

public class Application {
    private static final Path CONFIG_PATH = Paths.get("config.properties");
    public static final Properties properties = new Properties();

    public static void main(String ... args) {
        tryParseProperties();
        generator();

        final FileFilter filter = new FileFilter();
        filter.scanning();
    }

    private static void tryParseProperties() {
        try (final FileReader in = new FileReader(CONFIG_PATH.toFile())) {
            properties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void generator() {
        final Path sourceFile = Paths.get(Application.properties.getProperty("file-path-in"), Application.properties.getProperty("file-in-source"));
        final int limit = 1000;
        final List<Integer> randomInt = IntStream.generate(new Random()::nextInt).limit(limit).boxed().toList();

        try (final OutputStream out = new FileOutputStream(sourceFile.toFile(), false);
             final OutputStreamWriter outWriter = new OutputStreamWriter(out, StandardCharsets.UTF_8);
             final BufferedWriter buffer = new BufferedWriter(outWriter)) {
            for (Integer value : randomInt) {
                buffer.write(value.toString());
                buffer.newLine();
            }
            buffer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
