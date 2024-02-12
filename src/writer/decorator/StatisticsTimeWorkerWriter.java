package src.writer.decorator;

import src.writer.Writer;

import java.io.IOException;

public class StatisticsTimeWorkerWriter extends WriterDecorator {
    private long start = 0L;
    private long end = 0L;

    public StatisticsTimeWorkerWriter(final Writer writer) {
        super(writer);
        this.start = System.currentTimeMillis();
    }

    @Override
    public void write(String line) throws IOException {
        this.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        this.end = System.currentTimeMillis();
        System.out.println("Time work (ms): " + (this.end-this.start));
        this.writer.close();
    }

}
