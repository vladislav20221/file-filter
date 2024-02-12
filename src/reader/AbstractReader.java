package src.reader;

import src.reader.impl.IoReader;

import java.io.IOException;

public abstract class AbstractReader implements Reader {
    private final Reader baseReader;

    public AbstractReader(final String fileName) {
        this.baseReader = new IoReader(fileName);
    }

    @Override
    public String readLine() throws IOException {
        return this.baseReader.readLine();
    }

    @Override
    public void close() throws IOException {
        this.baseReader.close();
    }

}
