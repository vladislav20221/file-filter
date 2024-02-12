package src.writer.decorator;

import src.writer.Writer;

import java.io.IOException;

public class FileInformationWriter extends WriterDecorator {

    public FileInformationWriter(Writer writer) {
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
