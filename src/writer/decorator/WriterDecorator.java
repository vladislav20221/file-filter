package src.writer.decorator;

import src.writer.FilterWriter;

import java.io.IOException;

public abstract class WriterDecorator implements FilterWriter {
    protected final FilterWriter writer;

    public WriterDecorator(FilterWriter writer) {
        this.writer = writer;
    }

    @Override
    public void write(String line) throws IOException {
        this.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        this.writer.close();
    }

}
