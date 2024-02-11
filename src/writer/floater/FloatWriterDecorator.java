package src.writer.floater;

import src.writer.Writer;

import java.io.IOException;

public abstract class FloatWriterDecorator implements Writer {
    protected final Writer writer;

    public FloatWriterDecorator(final FloatWriter writer) {
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
