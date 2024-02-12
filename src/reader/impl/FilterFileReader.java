package src.reader.impl;

import src.Application;
import src.reader.AbstractReader;

public class FilterFileReader extends AbstractReader {

    public FilterFileReader() {
        super(Application.properties.getProperty("file-in-source"));
    }

}
