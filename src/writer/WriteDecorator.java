package src.writer;

import java.io.IOException;

public abstract class WriteDecorator implements Writer {
    protected final Writer writer;

    public WriteDecorator(Writer writer) {
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
