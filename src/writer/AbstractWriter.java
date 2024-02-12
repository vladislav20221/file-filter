package src.writer;

import src.Application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractWriter implements Writer {
    protected final String fileName;

    private final BufferedWriter data;

    public AbstractWriter(final String fileName) {
        final String filePrefix = Application.properties.getProperty("file-prefix");
        this.fileName = filePrefix.concat(fileName);

        final Path basePath = Paths.get(Application.properties.getProperty("file-path-out"));
        if (basePath.toFile().mkdirs()) {
            System.out.println("Create new directory: " + basePath.getFileName());
        }

        final int bufferSize = Integer.parseInt(Application.properties.getProperty("out-buffer-size"));
        final boolean rewrite = Boolean.parseBoolean(Application.properties.getProperty("file-out-rewrite"));

        final Path file = Paths.get(basePath.toFile().getPath(), this.fileName);

        try {
            final FileWriter out = new FileWriter(file.toFile(), rewrite);
            this.data = new BufferedWriter(out, bufferSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(final String line) throws IOException {
        this.data.write(line);
        this.data.newLine();
    }

    @Override
    public void close() throws IOException {
        this.data.flush();
        this.data.close();
    }

}
