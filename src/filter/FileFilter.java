package src.filter;

import src.enums.DataType;
import src.reader.FilterFileReader;
import src.reader.Reader;
import src.writer.Writer;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class FileFilter {
    private final Map<DataType, Writer> writerMap;
    private final Reader fileReader;

    public FileFilter(final Map<DataType, Writer> writerMap) {
        this.writerMap = writerMap;
        this.fileReader = new FilterFileReader();
    }
    
    public void scanning() {
        try {
            while (true) {
                final String line = this.fileReader.readLine();
                if (Objects.isNull(line)) {
                    break;
                }
                System.out.println("Check: " + line);
                if (line.matches("\\d+")) {
                    this.writerMap.get(DataType.INTEGER).write(line);
                } else if (line.matches("\\d+\\.\\d+")) {
                    this.writerMap.get(DataType.FLOAT).write(line);
                } else {
                    this.writerMap.get(DataType.STRING).write(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.writerMap.forEach((k, v) -> {
                try {
                    v.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    
}
