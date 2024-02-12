package src.writer.decorator;

import src.writer.Writer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class StatisticsWriter extends WriterDecorator {
    private final AtomicLong count = new AtomicLong(0L);

    public StatisticsWriter(final Writer writer) {
        super(writer);
    }

    @Override
    public void write(String line) throws IOException {
        count.incrementAndGet();
        super.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        System.out.println("Statistics total write: " + count.get());
        super.writer.close();
    }

}
