package src.reader.impl;

import src.Application;
import src.reader.FilterReader;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Path;

public class IoReader implements FilterReader {
    private final BufferedReader data;

    public IoReader(final Path targetFile) {
        final int bufferSize = Integer.parseInt(Application.properties.getProperty("in-buffer-size"));
        final String encoding = Application.properties.getProperty("encoding-in");

        try {
            final InputStream in = new FileInputStream(targetFile.toFile());
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
