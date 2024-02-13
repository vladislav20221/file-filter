package src.reader;

import src.reader.impl.IoReader;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public abstract class AbstractReader implements FilterReader {
    private final FilterReader baseReader;
    protected final Path targetFile;

    public AbstractReader(final Path targetFile) {
        Objects.requireNonNull(targetFile, "Target file reader is null !");
        this.targetFile = targetFile;
        this.baseReader = new IoReader(targetFile);
    }

    @Override
    public String readLine() throws IOException {
        return this.baseReader.readLine();
    }

    @Override
    public void close() throws IOException {
        this.baseReader.close();
    }

}
