package src.writer.decorator;

import src.writer.FilterWriter;

import java.io.IOException;

public class StatisticsTimeWorkerWriter extends WriterDecorator {
    private final long start;

    public StatisticsTimeWorkerWriter(final FilterWriter writer) {
        super(writer);
        this.start = System.currentTimeMillis();
    }

    @Override
    public void write(String line) throws IOException {
        this.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        final long end = System.currentTimeMillis();
        System.out.println("time work (ms): " + (end-this.start));
        this.writer.close();
    }

}
