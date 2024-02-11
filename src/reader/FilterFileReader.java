package src.reader;

import src.Application;

public class FilterFileReader extends AbstractReader {

    public FilterFileReader() {
        super(Application.properties.getProperty("file-in-source"));
    }

}
