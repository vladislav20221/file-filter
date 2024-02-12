package src.writer.decorator;

import src.writer.Writer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsStringWriter extends WriterDecorator {
    private final AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
    private final AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);

    public StatisticsStringWriter(final Writer writer) {
        super(writer);
    }

    @Override
    public void write(final String line) throws IOException {
        max.set(Integer.max(max.get(), line.length()));
        min.set(Integer.min(min.get(), line.length()));
        this.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        System.out.println("String statistics: min length = " + getMaxLength() + "\tmax length = " + getMinLength());
        this.writer.close();
    }

    public int getMaxLength() {
        return max.get();
    }

    public int getMinLength() {
        return min.get();
    }

}
