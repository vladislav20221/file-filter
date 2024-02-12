package src.writer.decorator;

import src.writer.Writer;

import java.io.IOException;

public class StatisticsWriter extends WriterDecorator {
    private Long count = 0L;

    public StatisticsWriter(final Writer writer) {
        super(writer);
    }

    @Override
    public void write(String line) throws IOException {
        count++;
        super.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        System.out.println("total write: " + count);
        super.writer.close();
    }

}
