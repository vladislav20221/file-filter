package src.writer.integer;

import src.Application;
import src.writer.AbstractWriter;

import java.io.IOException;

public class IntegerWriter extends AbstractWriter {

    public IntegerWriter() {
        super(Application.properties.getProperty("file-out-int"));
    }

    @Override
    public void close() throws IOException {
        System.out.println("Target file: " + super.baseFileName);
        super.close();
    }

}
