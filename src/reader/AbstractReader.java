package src.reader;

import src.Application;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class AbstractReader implements Reader, Closeable {
    private final BufferedReader data;

    public AbstractReader(final String fileName) {
        final int bufferSize = Integer.parseInt(Application.properties.getProperty("in-buffer-size"));
        final String filePath = Application.properties.getProperty("file-path-in");

        final Path path = Paths.get(filePath, fileName);
        try {
            final FileReader in = new FileReader(path.toFile());
            this.data = new BufferedReader(in, bufferSize);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String readLine() throws IOException {
        return data.readLine();
    }

    @Override
    public void close() throws IOException {
        this.data.close();
    }

}
