package src.writer.string;

import src.Application;
import src.writer.AbstractWriter;

public class StringWriter extends AbstractWriter {

    public StringWriter() {
        super(Application.properties.getProperty("file-out-string"));
    }

}
