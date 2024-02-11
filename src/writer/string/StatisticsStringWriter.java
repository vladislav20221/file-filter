package src.writer.string;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsStringWriter extends StringWriteDecorator {
    private final AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
    private final AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);

    public StatisticsStringWriter(final StringWriter writer) {
        super(writer);
    }

    @Override
    public void write(final String line) throws IOException {
        max.set(Integer.max(max.get(), line.length()));
        min.set(Integer.min(min.get(), line.length()));
        System.out.println("min: " + getMinLength() + "\tmax: " + getMaxLength());
        this.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        this.writer.close();
    }

    public int getMaxLength() {
        return max.get();
    }

    public int getMinLength() {
        return min.get();
    }

}
