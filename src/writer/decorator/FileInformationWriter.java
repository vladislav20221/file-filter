package src.writer.decorator;

import src.writer.FilterWriter;

import java.io.IOException;

/**
 * Добавляет действие вывода подробной информации о файле назначения.
 */
public class FileInformationWriter extends WriterDecorator {

    public FileInformationWriter(FilterWriter writer) {
        super(writer);
    }

    @Override
    public void write(String line) throws IOException {
        super.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        super.writer.close();
    }

}
