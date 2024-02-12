package src.writer.impl;

import src.Application;
import src.writer.AbstractWriter;

import java.io.IOException;

public class FloatWriter extends AbstractWriter {

    public FloatWriter() {
        super(Application.properties.getProperty("file-out-float"));
    }

    @Override
    public void write(String line) throws IOException {
        super.write(line);
    }

    @Override
    public void close() throws IOException {
        super.close();
    }

}
