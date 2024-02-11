package src.writer.integer;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsIntegerWriter extends IntegerWriterDecorator {
    private final AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
    private final AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
    private final AtomicInteger sum = new AtomicInteger(0);

    public StatisticsIntegerWriter(final IntegerWriter writer) {
        super(writer);
    }

    @Override
    public void write(String line) throws IOException {
        final int value = Integer.parseInt(line);
        sum.addAndGet(value);
        max.set(Integer.max(max.get(), value));
        min.set(Integer.min(min.get(), value));
        System.out.println("min: " + getMin() + "\tmax: " + getMax());
        super.writer.write(line);
    }

    @Override
    public void close() throws IOException {
        super.writer.close();
    }

    public int getMin() {
        return min.get();
    }

    public int getMax() {
        return max.get();
    }

    public int getSum() {
        return sum.get();
    }

    public float average(final int count) {
        return (float)sum.get()/(float) count;
    }

}
