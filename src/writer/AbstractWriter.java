package src.writer;

import src.writer.impl.IoWriter;

import java.io.IOException;

public abstract class AbstractWriter implements FilterWriter {
    protected final FilterWriter baseWriter;

    public AbstractWriter(final String fileName) {
        this.baseWriter = new IoWriter(fileName);
    }

    @Override
    public void write(final String line) throws IOException {
        this.baseWriter.write(line);
    }

    @Override
    public void close() throws IOException {
        this.baseWriter.close();
    }

}
