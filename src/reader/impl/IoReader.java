package src.reader.impl;

import src.Application;
import src.reader.Reader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

public class IoReader implements Reader {
    private final BufferedReader data;
    private final Path filePath;

    public IoReader(final String fileName) {
        final int bufferSize = Integer.parseInt(Application.properties.getProperty("in-buffer-size"));
        final String filePath = Application.properties.getProperty("file-path-in");
        final String encoding = Application.properties.getProperty("encoding-in");

        this.filePath = Paths.get(filePath, fileName);

        try {
            final InputStream in = new FileInputStream(this.filePath.toFile());
            final InputStreamReader inReader = new InputStreamReader(in, Charset.forName(encoding));

            this.data = new BufferedReader(inReader, bufferSize);
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
