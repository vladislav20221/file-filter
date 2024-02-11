package src.writer.floater;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class StatisticsFloatWriter extends FloatWriterDecorator {
    private final AtomicInteger max = new AtomicInteger(Integer.MIN_VALUE);
    private final AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
    private final AtomicInteger sum = new AtomicInteger(0);

    public StatisticsFloatWriter(final FloatWriter writer) {
        super(writer);
    }

    @Override
    public void write(String line) throws IOException {
        final int value = Float.floatToIntBits(Float.parseFloat(line));
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

    public float getMin() {
        return Float.intBitsToFloat(min.get());
    }

    public float getMax() {
        return Float.intBitsToFloat(max.get());
    }

    public float getSum() {
        return Float.intBitsToFloat(sum.get());
    }

    public float average(final int count) {
        return Float.intBitsToFloat(sum.get())/(float) count;
    }

}
