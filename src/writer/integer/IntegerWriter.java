package src.writer.integer;

import src.Application;
import src.writer.AbstractWriter;

public class IntegerWriter extends AbstractWriter {

    public IntegerWriter() {
        super(Application.properties.getProperty("file-out-int"));
    }

}
