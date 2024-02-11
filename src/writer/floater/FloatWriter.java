package src.writer.floater;

import src.Application;
import src.writer.AbstractWriter;

public class FloatWriter extends AbstractWriter {

    public FloatWriter() {
        super(Application.properties.getProperty("file-out-float"));
    }

}
