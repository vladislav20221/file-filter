package src.writer;

import src.Application;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractWriter implements Writer {
    protected final String baseFileName;

    private final BufferedWriter data;

    public AbstractWriter(final String fileName) {
        this.baseFileName = fileName;

        final String fileOutPath = Application.properties.getProperty("file-path-out");
        final String filePrefix = Application.properties.getProperty("file-prefix");
        final int bufferSize = Integer.parseInt(Application.properties.getProperty("out-buffer-size"));
        final boolean rewrite = Boolean.parseBoolean(Application.properties.getProperty("file-out-rewrite"));

        final Path path = Paths.get(fileOutPath, filePrefix.concat(baseFileName));

        try {
            final FileWriter out = new FileWriter(path.toFile(), rewrite);
            this.data = new BufferedWriter(out, bufferSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void write(String line) throws IOException {
        this.data.write(line);
        this.data.newLine();
    }

    @Override
    public void close() throws IOException {
        this.data.flush();
        this.data.close();
    }

}
