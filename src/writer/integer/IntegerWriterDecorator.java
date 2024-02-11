package src.writer.integer;

import src.writer.Writer;
import src.writer.integer.IntegerWriter;

import java.io.IOException;

public abstract class IntegerWriterDecorator implements Writer {
    protected final Writer writer;

    public IntegerWriterDecorator(final IntegerWriter writer) {
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
