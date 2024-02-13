package src;

import src.filter.FileFilter;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.Random;

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
        final String generateFileName = "source-%d.txt";
        final int max = Integer.parseInt(Application.properties.getProperty("generation-max"))+1;
        final int min = Integer.parseInt(Application.properties.getProperty("generation-min"));
        final int limit = Integer.parseInt(Application.properties.getProperty("generation-size"));

        for (int i = 0; i<4; ++i) {
            final Path sourceFile = Paths.get(Application.properties.getProperty("file-path-in"), generateFileName.formatted(i));
            final int bufferSize = Integer.parseInt(Application.properties.getProperty("in-buffer-size"));
            final String encoding = Application.properties.getProperty("encoding-in");

            final Random random = new Random();
            final List<Integer> randomInt =
                    random.ints(min, max)
                            .limit(limit)
                            .boxed().toList();

            try (final OutputStream out = new FileOutputStream(sourceFile.toFile(), false);
                 final OutputStreamWriter outWriter = new OutputStreamWriter(out, Charset.forName(encoding));
                 final BufferedWriter buffer = new BufferedWriter(outWriter, bufferSize)) {
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

}
