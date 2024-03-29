package src.filter;

import src.Application;
import src.enums.DataType;
import src.reader.impl.FilterFileReader;
import src.writer.FilterWriter;
import src.writer.WriterFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * Фильтрует входные данные из файлов <source> по фалйам назначнеия <target>.
 */
public class FileFilter {
    private final Map<DataType, FilterWriter> writers = new HashMap<>();
    private final List<FilterFileReader> readers;

    public FileFilter() {
        final Path filePath = Paths.get(Application.properties.getProperty("file-path-in"));
        this.readers = Stream.of(Objects.requireNonNull(filePath.toFile().listFiles()))
                            .filter(File::isFile)
                            .map(file -> Paths.get(filePath.toFile().getPath(), file.getName()))
                            .map(FilterFileReader::new)
                            .toList();
    }
    
    public void scanning() {
        try {
            for (FilterFileReader reader : this.readers) {
                tryScanFile(reader);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            tryCloseWriter();
            tryCloseReader();
        }
    }

    private void tryCloseWriter() {
        this.writers.forEach((dataType, writer) -> {
            try {
                System.out.println("Statistics type: " + dataType);
                writer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void tryCloseReader() {
        this.readers.forEach(reader -> {
            try {
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void tryScanFile(final FilterFileReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            tryHandleLine(line);
        }
    }

    // TODO: Рассмосреть другие наиболее оптимальные варианты создание экземпляров FilterWriter.
    private void tryHandleLine(final String line) throws IOException {
        if (line.matches(DataType.INTEGER.pattern())) {
            if (this.writers.get(DataType.INTEGER) == null) {
                this.writers.put(DataType.INTEGER, WriterFactory.createIntegerWriter());
            }
            this.writers.get(DataType.INTEGER).write(line);
        } else if (line.matches(DataType.FLOAT.pattern())) {
            if (this.writers.get(DataType.FLOAT) == null) {
                this.writers.put(DataType.FLOAT, WriterFactory.createFloatWriter());
            }
            this.writers.get(DataType.FLOAT).write(line);
        } else {
            if (this.writers.get(DataType.STRING) == null) {
                this.writers.put(DataType.STRING, WriterFactory.createStringWriter());
            }
            this.writers.get(DataType.STRING).write(line);
        }
    }
    
}
