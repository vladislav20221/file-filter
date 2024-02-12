package src.writer.string;

import src.Application;
import src.writer.AbstractWriter;

import java.io.IOException;

public class StringWriter extends AbstractWriter {

    public StringWriter() {
        super(Application.properties.getProperty("file-out-string"));
    }

    @Override
    public void close() throws IOException {
        System.out.println("Target file: " + super.baseFileName);
        super.close();
    }

}
