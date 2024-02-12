package src.writer.impl;

import src.Application;
import src.writer.Writer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IoWriter implements Writer {
    private final BufferedWriter data;
    private final Path filePath;

    public IoWriter(final String fileName) {
        final String filePrefix = Application.properties.getProperty("file-prefix");

        final Path basePath = Paths.get(Application.properties.getProperty("file-path-out"));
        if (basePath.toFile().mkdirs()) {
            System.out.println("Create new directory: " + basePath.getFileName());
        }

        final int bufferSize = Integer.parseInt(Application.properties.getProperty("out-buffer-size"));
        final boolean rewrite = Boolean.parseBoolean(Application.properties.getProperty("file-out-rewrite"));

        final String encoding = Application.properties.getProperty("encoding-out");
        this.filePath = Paths.get(basePath.toFile().getPath(), filePrefix.concat(fileName));

        try {
            final OutputStream out = new FileOutputStream(this.filePath.toFile(), rewrite);
            final OutputStreamWriter outWriter = new OutputStreamWriter(out, Charset.forName(encoding));
            this.data = new BufferedWriter(outWriter, bufferSize);
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
