package src.reader.impl;

import src.reader.AbstractReader;

import java.nio.file.Path;

public class FilterFileReader extends AbstractReader {

    public FilterFileReader(final Path targetFile) {
        super(targetFile);
    }

}
