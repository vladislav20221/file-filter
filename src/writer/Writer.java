package src.writer;

import java.io.Closeable;
import java.io.IOException;

public interface Writer extends Closeable {
    void write(String line) throws IOException;
}
