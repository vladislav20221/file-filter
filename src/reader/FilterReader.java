package src.reader;

import java.io.Closeable;
import java.io.IOException;

public interface FilterReader extends Closeable {
    String readLine() throws IOException;
}
