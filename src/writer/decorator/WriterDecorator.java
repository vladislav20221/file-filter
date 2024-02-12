package src.writer.decorator;

import src.writer.Writer;

import java.io.IOException;

public abstract class WriterDecorator implements Writer {
    protected final Writer writer;

    public WriterDecorator(Writer writer) {
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
