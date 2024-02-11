package src.writer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

public class StatisticsWriter extends WriteDecorator {
    private final AtomicLong count = new AtomicLong(0L);

    public StatisticsWriter(final Writer writer) {
        super(writer);
    }

    @Override
    public void write(String line) throws IOException {
        count.incrementAndGet();
        System.out.println("count: " + count.get());
        super.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        super.writer.close();
    }

}
