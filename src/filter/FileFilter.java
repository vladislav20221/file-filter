package src.filter;

import src.enums.DataType;
import src.reader.FilterFileReader;
import src.reader.Reader;
import src.writer.Writer;
import src.writer.WriterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileFilter {
    private final Map<DataType, Writer> writerMap = new HashMap<>();
    private final Reader fileReader;

    public FileFilter() {
        this.fileReader = new FilterFileReader();
    }
    
    public void scanning() {
        try {
            while (true) {
                final String line = this.fileReader.readLine();
                if (Objects.isNull(line)) {
                    break;
                }
                if (line.matches(DataType.INTEGER.pattern())) {
                    this.writerMap.putIfAbsent(DataType.INTEGER, WriterFactory.createIntegerWriter());
                    this.writerMap.get(DataType.INTEGER).write(line);
                } else if (line.matches(DataType.FLOAT.pattern())) {
                    this.writerMap.putIfAbsent(DataType.FLOAT, WriterFactory.createFloatWriter());
                    this.writerMap.get(DataType.FLOAT).write(line);
                } else {
                    this.writerMap.putIfAbsent(DataType.STRING, WriterFactory.createStringWriter());
                    this.writerMap.get(DataType.STRING).write(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            this.writerMap.forEach((k, v) -> {
                try {
                    System.out.println("Statistics type: " + k);
                    v.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
    
}
