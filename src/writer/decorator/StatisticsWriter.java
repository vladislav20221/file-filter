package src.writer.decorator;

import src.writer.FilterWriter;

import java.io.IOException;

public class StatisticsWriter extends WriterDecorator {
    private Long count = 0L;

    public StatisticsWriter(final FilterWriter writer) {
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
