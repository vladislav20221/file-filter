package src.writer.floater;

import src.Application;
import src.writer.AbstractWriter;

import java.io.IOException;

public class FloatWriter extends AbstractWriter {

    public FloatWriter() {
        super(Application.properties.getProperty("file-out-float"));
    }

    @Override
    public void close() throws IOException {
        System.out.println("Target file: " + super.baseFileName);
        super.close();
    }

}
