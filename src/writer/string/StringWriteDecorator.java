package src.writer.string;

import src.writer.Writer;

import java.io.IOException;

public abstract class StringWriteDecorator implements Writer {
    protected final Writer writer;

    public StringWriteDecorator(final StringWriter writer) {
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
